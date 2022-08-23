package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.core.StageRunManager;
import com.maukaim.bulo.executors.data.StageDefinitionStore;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.executors.data.models.StageInputDefinition;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.data.result.StageRunStatus;
import com.maukaim.bulo.executors.data.stages.Parameter;
import com.maukaim.bulo.executors.data.stages.Stage;

import java.util.*;
import java.util.stream.Collectors;

//TODO: All the validation logic should go to validators.
public class StageRunEventProcessorImpl implements StageRunEventProcessor {
    private final StageRunManager runnerManager;
    private final StageStore stageStore;
    private final StageRunResultStore stageRunResultStore;
    private final StageDefinitionStore definitionStore;

    public StageRunEventProcessorImpl(StageRunManager runnerManager,
                                      StageStore stageStore,
                                      StageRunResultStore stageRunResultStore,
                                      StageDefinitionStore definitionStore) {
        this.runnerManager = runnerManager;
        this.stageStore = stageStore;
        this.stageRunResultStore = stageRunResultStore;
        this.definitionStore = definitionStore;
    }

    @Override
    public void onStageRunRequest(String globalStageId, String stageRunId, Map<String, Map<String, Set<String>>> ancestorsOutputByInputName) {
        this.stageRunResultStore.put(StageRunResult.of(stageRunId, StageRunStatus.ACKNOWLEDGED));

        Stage stage = this.stageStore.getById(globalStageId);
        if (stage == null) {
            StageRunResult cancelledStageRun = StageRunResult.of(stageRunId, StageRunStatus.CANCELLED);
            this.stageRunResultStore.put(cancelledStageRun);
            return;
        }

        Map<String, List<String>> inputsByName = new HashMap<>();
        List<String> failReasons = new ArrayList<>();
        for (String inputName : ancestorsOutputByInputName.keySet()) {
            Map<String, Set<String>> outputNamesByAncestorRunIds = ancestorsOutputByInputName.get(inputName);

            List<String> inputs = new ArrayList<>();
            for (String ancestorRunId : outputNamesByAncestorRunIds.keySet()) {
                StageRunResult runResult = this.stageRunResultStore.getById(ancestorRunId);
                if (runResult == null) {
                    failReasons.add(String.format("Ancestor's runId %s does not exist.", ancestorRunId));
                } else if (runResult.getStatus() != StageRunStatus.SUCCESS) {
                    failReasons.add(String.format("Ancestor's run (%s) is not successfully terminated. Current status: %s.",
                            ancestorRunId, runResult.getStatus()));
                } else {
                    List<String> outputs = outputNamesByAncestorRunIds.get(ancestorRunId).stream()
                            .map(outputName -> runResult.getOutputs().get(outputName))
                            .collect(Collectors.toList());
                    inputs.addAll(outputs);
                }
            }
            if (failReasons.size() != 0) {
                break;
            }
            inputsByName.put(inputName, inputs);
        }

        if (failReasons.size() != 0) {
            StageRunResult cancelledStageRun = StageRunResult.of(stageRunId, StageRunStatus.CANCELLED);
            this.stageRunResultStore.put(cancelledStageRun);
            return;
        }

        List<Parameter> parameters = stage.getParameters();
        if (parameters == null) {
            StageRunResult cancelledStageRun = StageRunResult.of(stageRunId, StageRunStatus.CANCELLED);
            this.stageRunResultStore.put(cancelledStageRun);
            return;
        }

        Map<String, String> parametersByName = parameters.stream().collect(Collectors.toMap(p -> p.getName(), p -> p.getValue()));

        String definitionId = stage.getDefinitionId();
        StageRunConfig stageRunConfig = new StageRunConfig(stageRunId,
                definitionId,
                resolve(definitionId, inputsByName),
                parametersByName);
        boolean hasBeenAccepted = this.runnerManager.submit(stageRunConfig);
        if (!hasBeenAccepted) {
            StageRunResult cancelledStageRun = StageRunResult.of(stageRunId, StageRunStatus.CANCELLED);
            this.stageRunResultStore.put(cancelledStageRun);
        }
    }

    private Map<String, String> resolve(String definitionId, Map<String, List<String>> inputsByName) {
        return inputsByName.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> validateAndMap(definitionId, e.getKey(), e.getValue()))
                );
    }

    private String validateAndMap(String definitionId, String inputName, List<String> value) {
        String result = null;
        if (value.size() > 1) {
            throw new RuntimeException("Sorry but we do not support inputs from multiple output source. Only 1 output can be mapped to an input at the moment.");
        } else if (value.size() == 0) {
            StageDefinition definition = this.definitionStore.getById(definitionId);
            StageInputDefinition stageInputDefinition = definition.getInputsByName().get(inputName);
            if (stageInputDefinition.isRequired()) {
                throw new RuntimeException("Following input is missing but required:" + inputName);
            }
        } else {
            result = value.iterator().next();
        }
        return result;
    }
}
