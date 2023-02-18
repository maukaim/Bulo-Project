package com.maukaim.bulo.flows.ms.data.lifecycle.adapters.impl;

import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageInputDefinitionAdapter;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageOutputDefinitionAdapter;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageDefinitionAdapter;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;
import com.maukaim.bulo.io.flows.system.definition.StageInputDefinitionDto;
import com.maukaim.bulo.io.flows.system.definition.StageOutputDefinitionDto;
import com.maukaim.bulo.io.flows.system.definition.stageDefinitionDto;

import java.util.Map;
import java.util.stream.Collectors;

public class StageDefinitionAdapterImpl implements StageDefinitionAdapter {
    private final ParameterDefinitionAdapter definitionAdapter;
    private final StageInputDefinitionAdapter stageInputDefinitionAdapter;
    private final StageOutputDefinitionAdapter stageOutputDefinitionAdapter;

    public StageDefinitionAdapterImpl(ParameterDefinitionAdapter definitionAdapter, StageInputDefinitionAdapter stageInputDefinitionAdapter, StageOutputDefinitionAdapter stageOutputDefinitionAdapter) {
        this.definitionAdapter = definitionAdapter;
        this.stageInputDefinitionAdapter = stageInputDefinitionAdapter;
        this.stageOutputDefinitionAdapter = stageOutputDefinitionAdapter;
    }

    @Override
    public StageDefinition adapte(stageDefinitionDto dto) {
        return new StageDefinition(
                dto.getDefinitionId(),
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
