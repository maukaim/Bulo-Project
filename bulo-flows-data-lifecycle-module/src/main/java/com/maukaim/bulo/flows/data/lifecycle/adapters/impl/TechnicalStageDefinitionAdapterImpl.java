package com.maukaim.bulo.flows.data.lifecycle.adapters.impl;


import com.maukaim.bulo.flows.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.flows.data.lifecycle.adapters.StageInputDefinitionAdapter;
import com.maukaim.bulo.flows.data.lifecycle.adapters.StageOutputDefinitionAdapter;
import com.maukaim.bulo.flows.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;
import com.maukaim.bulo.flows.io.definition.StageInputDefinitionDto;
import com.maukaim.bulo.flows.io.definition.StageOutputDefinitionDto;
import com.maukaim.bulo.flows.io.definition.TechnicalStageDefinitionDto;

import java.util.Map;
import java.util.stream.Collectors;

public class TechnicalStageDefinitionAdapterImpl implements TechnicalStageDefinitionAdapter {
    private final ParameterDefinitionAdapter definitionAdapter;
    private final StageInputDefinitionAdapter stageInputDefinitionAdapter;
    private final StageOutputDefinitionAdapter stageOutputDefinitionAdapter;

    public TechnicalStageDefinitionAdapterImpl(ParameterDefinitionAdapter definitionAdapter, StageInputDefinitionAdapter stageInputDefinitionAdapter, StageOutputDefinitionAdapter stageOutputDefinitionAdapter) {
        this.definitionAdapter = definitionAdapter;
        this.stageInputDefinitionAdapter = stageInputDefinitionAdapter;
        this.stageOutputDefinitionAdapter = stageOutputDefinitionAdapter;
    }

    @Override
    public StageDefinition adapte(TechnicalStageDefinitionDto dto) {
        return new StageDefinition(
                dto.getTechnicalStageDefinitionId(),
                resolveInputs(dto.getInputsByName()),
                resolveOutputs(dto.getOutputsByName()),
                dto.getParameters().stream().map(definitionAdapter::adapte).collect(Collectors.toList())
        );
    }

    private Map<String, StageInputDefinition> resolveInputs(Map<String, StageInputDefinitionDto> inputDtosByName) {
        if (inputDtosByName == null) {
            return null;
        }
        return inputDtosByName.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> this.stageInputDefinitionAdapter.adapte(entry.getValue())));
    }

    private Map<String, StageOutputDefinition> resolveOutputs(Map<String, StageOutputDefinitionDto> inputDtosByName) {
        if (inputDtosByName == null) {
            return null;
        }
        return inputDtosByName.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> this.stageOutputDefinitionAdapter.adapte(entry.getValue())));
    }
}
