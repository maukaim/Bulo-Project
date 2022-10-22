package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl;

import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageInputDefinitionAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageOutputDefinitionAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageDefinitionAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StageDefinitionAdapterImpl implements StageDefinitionAdapter {
    private final StageInputDefinitionAdapter inputDefinitionAdapter;
    private final StageOutputDefinitionAdapter outputDefinitionAdapter;
    private final ParameterDefinitionAdapter parameterDefinitionAdapter;

    public StageDefinitionAdapterImpl(StageInputDefinitionAdapter inputDefinitionAdapter, StageOutputDefinitionAdapter outputDefinitionAdapter, ParameterDefinitionAdapter parameterDefinitionAdapter) {
        this.inputDefinitionAdapter = inputDefinitionAdapter;
        this.outputDefinitionAdapter = outputDefinitionAdapter;
        this.parameterDefinitionAdapter = parameterDefinitionAdapter;
    }

    @Override
    public com.maukaim.bulo.stages.models.definition.StageDefinition adapteStageModule(com.maukaim.bulo.definitions.data.definition.StageDefinition definition) {
        return definition == null ? null : switch (definition.getStageDefinitionType()) {
            case FUNCTIONAL ->
                    throw new UnsupportedOperationException("StageModule does not support FunctionalStage yet.");
            case TECHNICAL ->
                    adapteStageModule((com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition) definition);
        };
    }

    private com.maukaim.bulo.stages.models.definition.StageDefinition adapteStageModule(com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition definition) {
        return new com.maukaim.bulo.stages.models.definition.StageDefinition(
                definition.getId(),
                resolveParameterStageModule(definition.getParameters())
        );
    }

    private List<com.maukaim.bulo.stages.models.definition.ParameterDefinition> resolveParameterStageModule(List<ParameterDefinition> parameters) {
        return parameters == null ? List.of() : parameters.stream()
                .map(this.parameterDefinitionAdapter::adapterStageModule)
                .collect(Collectors.toList());
    }

    @Override
    public StageDefinition adapteFlowModule(com.maukaim.bulo.definitions.data.definition.StageDefinition definition) {
        return definition == null ? null : switch (definition.getStageDefinitionType()) {
            case FUNCTIONAL -> throw new UnsupportedOperationException("Not yet supported by FlowModule");
            case TECHNICAL -> adapteFlowModule((com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition) definition);
        };
    }

    private StageDefinition adapteFlowModule(com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition definition) {
        return new StageDefinition(
                definition.getId(),
                resolveInputFlowModule(definition.getInputsByName()),
                resolveOutputFlowModule(definition.getOutputsByName()),
                resolveParameterFlowModule(definition.getParameters())
        );
    }

    private List<com.maukaim.bulo.flows.data.models.definition.ParameterDefinition> resolveParameterFlowModule(List<ParameterDefinition> parameters) {
        return parameters == null ? List.of() : parameters.stream()
                .map(this.parameterDefinitionAdapter::adapteFlowModule)
                .collect(Collectors.toList());
    }

    private Map<String, com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition> resolveOutputFlowModule(Map<String, StageOutputDefinition> outputsByName) {
        return outputsByName == null ? new HashMap<>() : outputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.outputDefinitionAdapter.adapteFlowModule(entry.getValue())
                ));
    }

    private Map<String, com.maukaim.bulo.flows.data.models.definition.StageInputDefinition> resolveInputFlowModule(Map<String, StageInputDefinition> inputsByName) {
        return inputsByName == null ? new HashMap<>() : inputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.inputDefinitionAdapter.adapteFlowModule(entry.getValue())
                ));
    }

    @Override
    public com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition adapteFromExecutorModule(com.maukaim.bulo.executors.data.models.StageDefinition stageDefinition) {
        return stageDefinition == null ? null : new com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition(
                stageDefinition.getTechnicalStageDefinitionId(),
                resolveInputsFromExecutorModule(stageDefinition.getInputsByName()),
                resolveOutputsFromExecutorModule(stageDefinition.getOutputsByName()),
                resolveParametersFromExecutorModule(stageDefinition.getParameters())
        );
    }

    private List<ParameterDefinition> resolveParametersFromExecutorModule(List<com.maukaim.bulo.executors.data.models.ParameterDefinition> parameters) {
        return parameters == null ? List.of() : parameters.stream()
                .map(this.parameterDefinitionAdapter::adapteFromExecutorModule)
                .collect(Collectors.toList());
    }

    private Map<String, StageOutputDefinition> resolveOutputsFromExecutorModule(Map<String, com.maukaim.bulo.executors.data.models.StageOutputDefinition> outputsByName) {
        return outputsByName == null ? new HashMap<>() : outputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> this.outputDefinitionAdapter.adapteFromExecutorModule(entry.getValue())
                ));
    }

    private Map<String, StageInputDefinition> resolveInputsFromExecutorModule(Map<String, com.maukaim.bulo.executors.data.models.StageInputDefinition> inputsByName) {
        return inputsByName == null ? new HashMap<>() : inputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> this.inputDefinitionAdapter.adapteFromExecutorModule(entry.getValue())
                ));
    }
}
