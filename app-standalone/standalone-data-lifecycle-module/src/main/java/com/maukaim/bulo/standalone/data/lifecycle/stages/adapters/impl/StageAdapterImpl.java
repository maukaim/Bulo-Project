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
    public com.maukaim.bulo.executors.data.stages.Stage adapteExecutorModule(Stage stage) {
        return stage == null ? null : switch (stage.getStageType()){
            case TECHNICAL -> adapteTechnicalStageExecutorModule((TechnicalStage)stage);
            case FUNCTIONAL -> throw new UnsupportedDataMethodException("Functional stage not yet supported by Executor module.");
        };
    }

    @Override
    public com.maukaim.bulo.definitions.data.stage.Stage adapteDefinitionModule(Stage stage) {
        return stage == null ? null : switch (stage.getStageType()){
            case TECHNICAL -> adapteTechnicalStageDefinitionModule((TechnicalStage)stage);
            case FUNCTIONAL -> adapteFunctionalStageDefinitionModule((FunctionalStage)stage);
        };
    }

    private com.maukaim.bulo.definitions.data.stage.FunctionalStage adapteFunctionalStageDefinitionModule(FunctionalStage stage) {
        return new com.maukaim.bulo.definitions.data.stage.FunctionalStage(
                stage.getStageId(),
                resolveParametersDefinitionModule(stage.getParameters()),
                stage.getDefinitionId()
        );
    }

    private com.maukaim.bulo.definitions.data.stage.TechnicalStage adapteTechnicalStageDefinitionModule(TechnicalStage stage) {
        return new com.maukaim.bulo.definitions.data.stage.TechnicalStage(
                stage.getStageId(),
                resolveParametersDefinitionModule(stage.getParameters()),
                stage.getDefinitionId()
        );
    }

    private com.maukaim.bulo.flows.data.models.stage.FunctionalStage adapteFunctionalStageFlowModule(FunctionalStage stage) {
        return new com.maukaim.bulo.flows.data.models.stage.FunctionalStage(
                stage.getStageId(),
                stage.getDefinitionId(),
                resolveParametersFlowModule(stage.getParameters()));
    }

    private com.maukaim.bulo.flows.data.models.stage.TechnicalStage adapteTechnicalStageFlowModule(TechnicalStage stage) {
        return new com.maukaim.bulo.flows.data.models.stage.TechnicalStage(
                stage.getStageId(),
                resolveParametersFlowModule(stage.getParameters()),
                stage.getDefinitionId());
    }

    private com.maukaim.bulo.executors.data.stages.Stage adapteTechnicalStageExecutorModule(TechnicalStage stage) {
        return new com.maukaim.bulo.executors.data.stages.Stage(
                stage.getStageId(),
                resolveParametersExecutorModule(stage.getParameters()),
                stage.getDefinitionId()
        );
    }

    private List<com.maukaim.bulo.definitions.data.stage.Parameter> resolveParametersDefinitionModule(List<Parameter> parameters) {
        return parameters == null ? List.of() : parameters.stream()
                .map(parameterAdapter::adapteDefinitionModule)
                .collect(Collectors.toList());
    }

    private List<com.maukaim.bulo.executors.data.stages.Parameter> resolveParametersExecutorModule(List<Parameter> parameters) {
        return parameters == null ? List.of() : parameters.stream()
                .map(parameterAdapter::adapteExecutorModule)
                .collect(Collectors.toList());
    }

    private List<com.maukaim.bulo.flows.data.models.stage.Parameter> resolveParametersFlowModule(List<Parameter> parameters) {
        return parameters == null ? List.of() : parameters.stream()
                .map(parameterAdapter::adapteFlowModule)
                .collect(Collectors.toList());
    }
}
