package com.maukaim.bulo.executor.core;

import com.maukaim.bulo.executor.core.api.TechnicalStageDefinitionStore;
import com.maukaim.bulo.executor.core.api.TechnicalStageRunResultStore;
import com.maukaim.bulo.executor.core.api.TechnicalStageRunner;
import com.maukaim.bulo.executor.core.api.TechnicalStageStore;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageInputDefinition;
import com.maukaim.bulo.executor.core.api.run.result.StageRunStatus;
import com.maukaim.bulo.executor.core.api.run.result.TechnicalStageRunResult;
import com.maukaim.bulo.executor.core.api.stages.Parameter;
import com.maukaim.bulo.executor.core.api.stages.TechnicalStage;

import java.util.*;
import java.util.stream.Collectors;

public class StageRunServiceImpl implements StageRunService {
    private final TechnicalStageRunnerManager runnerManager;
    private final TechnicalStageStore technicalStageStore;
    private final TechnicalStageRunResultStore technicalStageRunResultStore;
    private final ExecutorService executorService;
    private final TechnicalStageDefinitionStore definitionStore; //TODO: Too much dependencies !

    public StageRunServiceImpl(TechnicalStageRunnerManager runnerManager,
                               TechnicalStageStore technicalStageStore,
                               TechnicalStageRunResultStore technicalStageRunResultStore,
                               ExecutorService executorService, TechnicalStageDefinitionStore definitionStore) {
        this.executorService = executorService;
        this.runnerManager = runnerManager;
        this.technicalStageStore = technicalStageStore;
        this.technicalStageRunResultStore = technicalStageRunResultStore;
        this.definitionStore = definitionStore;
    }

    @Override
    public void onStageRunRequest(String globalStageId, String stageRunId, Map<String, Map<String, Set<String>>> ancestorsOutputByInputName) {
        TechnicalStage technicalStage = this.technicalStageStore.getById(globalStageId);

        if (technicalStage == null) {
            TechnicalStageRunResult cancelledStageRun = new TechnicalStageRunResult(stageRunId, StageRunStatus.CANCELLED, Map.of());
            this.technicalStageRunResultStore.put(cancelledStageRun);
            return;
        }

        String definitionId = technicalStage.getDefinitionId();
        TechnicalStageRunner runner = this.runnerManager.getByDefinitionId(definitionId);
        if (runner == null) {
            TechnicalStageRunResult cancelledStageRun = new TechnicalStageRunResult(stageRunId, StageRunStatus.CANCELLED, Map.of());
            this.technicalStageRunResultStore.put(cancelledStageRun);
            return;
        }

        Map<String, List<String>> inputsByName = new HashMap<>();
        List<String> failReasons = new ArrayList<>();
        for (String inputName : ancestorsOutputByInputName.keySet()) {
            Map<String, Set<String>> outputNamesByAncestorRunIds = ancestorsOutputByInputName.get(inputName);

            List<String> inputs = new ArrayList<>();
            for (String ancestorRunId : outputNamesByAncestorRunIds.keySet()) {
                TechnicalStageRunResult runResult = this.technicalStageRunResultStore.getById(ancestorRunId);
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
            TechnicalStageRunResult cancelledStageRun = new TechnicalStageRunResult(stageRunId, StageRunStatus.CANCELLED, Map.of());
            this.technicalStageRunResultStore.put(cancelledStageRun);
            return;
        }
        List<Parameter> parameters = technicalStage.getParameters();
        if (parameters == null) {
            TechnicalStageRunResult cancelledStageRun = new TechnicalStageRunResult(stageRunId, StageRunStatus.CANCELLED, Map.of());
            this.technicalStageRunResultStore.put(cancelledStageRun);
            return;
        }

        Map<String, String> parametersByName = parameters.stream().collect(Collectors.toMap(p -> p.getName(), p -> p.getValue()));
        StageRunWrapper stageRunWrapper = new StageRunWrapper(definitionId, runner, inputsByName, parametersByName);
        this.executorService.executeAsync(stageRunWrapper);
    }

    //TODO: Bad design, maybe rethink how StageRunService works with ExecutorService, etc...)
    public class StageRunWrapper implements Runnable {

        private String definitionId;
        private TechnicalStageRunner runner;
        private Map<String, List<String>> inputsByName;
        private final Map<String, String> parametersByName;

        public StageRunWrapper(String definitionId, TechnicalStageRunner runner, Map<String, List<String>> inputsByName, Map<String, String> parametersByName) {
            this.definitionId = definitionId;
            this.runner = runner;
            this.inputsByName = inputsByName;
            this.parametersByName = parametersByName;
        }

        @Override
        public void run() {
            Map<String, String> inputByName = inputsByName.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey(), e -> validateAndMap(definitionId, e.getKey(), e.getValue())));
            runner.run(inputByName,parametersByName);
        }

        private String validateAndMap(String definitionId, String inputName, List<String> value) {
            String result = null;
            if (value.size() > 1) {
                throw new RuntimeException("Sorry but we do not support inputs from multiple output source. Only 1 output can be mapped to an input at the moment.");
            } else if (value.size() == 0) {
                TechnicalStageDefinition definition = definitionStore.getById(definitionId);
                TechnicalStageInputDefinition technicalStageInputDefinition = definition.getInputsByName().get(inputName);
                if(technicalStageInputDefinition.isRequired()){
                    throw new RuntimeException("Following input is missing but required:" + inputName);
                }
            } else {
                result = value.iterator().next();
            }
            return result;
        }
    }
}
