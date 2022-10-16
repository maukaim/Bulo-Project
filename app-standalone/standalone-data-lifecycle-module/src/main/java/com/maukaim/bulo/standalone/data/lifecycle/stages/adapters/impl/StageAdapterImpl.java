package com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.impl;

import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.Parameter;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;
import com.maukaim.bulo.standalone.data.lifecycle.UnsupportedDataMethodException;
import com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.ParameterAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.StageAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class StageAdapterImpl implements StageAdapter {
    private final ParameterAdapter parameterAdapter;

    public StageAdapterImpl(ParameterAdapter parameterAdapter) {
        this.parameterAdapter = parameterAdapter;
    }

    @Override
    public com.maukaim.bulo.flows.data.models.stage.Stage adapteFlowModule(Stage stage) {
        return stage == null ? null : switch (stage.getStageType()){
            case TECHNICAL -> adapteTechnicalStageFlowModule((TechnicalStage)stage);
            case FUNCTIONAL -> adapteFunctionalStageFlowModule((FunctionalStage)stage);
        };
    }

    @Override
    public com.maukaim.bulo.executors.data.stages.Stage adapteExecutorModule(com.maukaim.bulo.stages.models.stage.Stage stage) {
        return stage == null ? null : switch (stage.getStageType()){
            case TECHNICAL -> adapteTechnicalStageExecutorModule((TechnicalStage)stage);
            case FUNCTIONAL -> throw new UnsupportedDataMethodException("Functional stage not supported by Executor module.");
        };
    }

    private com.maukaim.bulo.executors.data.stages.Stage adapteTechnicalStageExecutorModule(TechnicalStage stage) {
        return new com.maukaim.bulo.executors.data.stages.Stage(
                stage.getStageId(),
                resolveParametersExecutorModule(stage.getParameters()),
                stage.getDefinitionId()
        );
    }

    private List<com.maukaim.bulo.executors.data.stages.Parameter> resolveParametersExecutorModule(List<Parameter> parameters) {
        return parameters == null ? List.of() : parameters.stream()
                .map(parameterAdapter::adapteExecutorModule)
                .collect(Collectors.toList());
    }

    private com.maukaim.bulo.flows.data.models.stage.Stage adapteFunctionalStageFlowModule(FunctionalStage stage) {
        return new com.maukaim.bulo.flows.data.models.stage.FunctionalStage(
                stage.getStageId(),
                resolveParametersFlowModule(stage.getParameters()));
    }

    private com.maukaim.bulo.flows.data.models.stage.Stage adapteTechnicalStageFlowModule(TechnicalStage stage) {
        return new com.maukaim.bulo.flows.data.models.stage.TechnicalStage(
                stage.getStageId(),
                resolveParametersFlowModule(stage.getParameters()),
                stage.getDefinitionId());
    }

    private List<com.maukaim.bulo.flows.data.models.stage.Parameter> resolveParametersFlowModule(List<Parameter> parameters) {
        return parameters == null ? List.of() : parameters.stream()
                .map(parameterAdapter::adapteFlowModule)
                .collect(Collectors.toList());
    }
}
