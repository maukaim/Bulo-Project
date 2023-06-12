package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.core.StageRunManager;
import com.maukaim.bulo.executors.core.StageRunProcessor;
import com.maukaim.bulo.executors.data.StageDefinitionStore;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.data.result.StageRunStatus;
import com.maukaim.bulo.executors.data.runs.StageRunAncestor;
import com.maukaim.bulo.executors.data.runs.StageRunDependency;
import com.maukaim.bulo.executors.data.stages.Parameter;
import com.maukaim.bulo.executors.data.stages.Stage;
import com.maukaim.bulo.runners.api.models.StageDefinition;
import com.maukaim.bulo.runners.api.models.StageInputDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class StageRunProcessorImpl implements StageRunProcessor {
    private final StageRunManager runnerManager;
    private final StageStore stageStore;
    private final StageRunResultStore stageRunResultStore;
    private final StageDefinitionStore definitionStore;

    public StageRunProcessorImpl(StageRunManager runnerManager,
                                 StageStore stageStore,
                                 StageRunResultStore stageRunResultStore,
                                 StageDefinitionStore definitionStore) {
        this.runnerManager = runnerManager;
        this.stageStore = stageStore;
        this.stageRunResultStore = stageRunResultStore;
        this.definitionStore = definitionStore;
    }

    @Override
    public void onRunRequest(String stageId, String stageRunId, Set<StageRunDependency> dependencies) {
        this.stageRunResultStore.put(StageRunResult.of(stageRunId, StageRunStatus.ACKNOWLEDGED));

        Stage stage = this.stageStore.getById(stageId);
        if (stage == null) {
            StageRunResult cancelledStageRun = StageRunResult.of(stageRunId, StageRunStatus.CANCELLED);
            this.stageRunResultStore.put(cancelledStageRun);
            return;
        }

        Map<String, List<String>> inputsByName = new HashMap<>();
        List<String> cancelReasons = new ArrayList<>();
        for (StageRunDependency dependency : dependencies) {
            String inputName = dependency.getInputId();
            Set<StageRunAncestor> ancestors = dependency.getAncestors();

            List<String> inputs = new ArrayList<>();
            for (StageRunAncestor ancestor : ancestors) {
                String ancestorRunId = ancestor.getStageRunId();
                StageRunResult ancestorRunResult = this.stageRunResultStore.getById(ancestorRunId);
                if (ancestorRunResult == null) {
                    cancelReasons.add(String.format("Ancestor's runId %s does not exist.", ancestorRunId)); // When StageRunResult log -> add to it
                } else if (ancestorRunResult.getStatus() != StageRunStatus.SUCCESS) {
                    cancelReasons.add(String.format("Ancestor's run (%s) is not successfully terminated. Current status: %s.", // When StageRunResult log -> add to it
                            ancestorRunId, ancestorRunResult.getStatus()));
                } else {
                    List<String> outputs = ancestor.getOutputIds().stream()
                            .map(outputName -> ancestorRunResult.getOutputs().get(outputName))
                            .filter(Objects::nonNull)
                            .toList();
                    inputs.addAll(outputs);
                }
            }
            if (cancelReasons.size() != 0) {
                break;
            }
            inputsByName.put(inputName, inputs);
        }

        if (cancelReasons.size() != 0) {
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

        Map<String, String> parametersByName = parameters.stream().collect(Collectors.toMap(Parameter::getName, Parameter::getValue));

        String definitionId = stage.getDefinitionId();
        Map<String, String> runConfigInputMap;
        try{
            runConfigInputMap = resolve(definitionId, inputsByName);
        }catch (RuntimeException ignored){ // When StageRunResult log -> add exception's message to it
            StageRunResult cancelledStageRun = StageRunResult.of(stageRunId, StageRunStatus.CANCELLED);
            this.stageRunResultStore.put(cancelledStageRun);
            return;
        }
        StageRunConfig stageRunConfig = new StageRunConfig(stageRunId,
                definitionId,
                runConfigInputMap,
                parametersByName);
        boolean hasBeenAccepted = this.runnerManager.submit(stageRunConfig);
        if (!hasBeenAccepted) {
            StageRunResult cancelledStageRun = StageRunResult.of(stageRunId, StageRunStatus.CANCELLED);
            this.stageRunResultStore.put(cancelledStageRun);
        }
    }

    @Override
    public void onCancelRequest(String stageRunId) {
        boolean isCancelled = this.runnerManager.cancel(stageRunId);
        if (isCancelled) {
            StageRunResult cancelledStageRun = StageRunResult.of(stageRunId, StageRunStatus.CANCELLED);
            this.stageRunResultStore.put(cancelledStageRun);
        }
    }

    private Map<String, String> resolve(String definitionId, Map<String, List<String>> inputsByName) {
        return inputsByName.entrySet().stream()
                .map(entry -> {
                    String ancestorOutputId = validateAndMap(definitionId, entry.getKey(), entry.getValue());
                    return ancestorOutputId == null ?
                            null :
                            Map.entry(entry.getKey(), ancestorOutputId);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private String validateAndMap(String definitionId, String inputName, List<String> value) {
        String result = null;
        if (value.size() > 1) {
            throw new RuntimeException("Sorry but we do not support inputs from multiple output source yet. Only 1 output can be mapped to an input at the moment.");
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
