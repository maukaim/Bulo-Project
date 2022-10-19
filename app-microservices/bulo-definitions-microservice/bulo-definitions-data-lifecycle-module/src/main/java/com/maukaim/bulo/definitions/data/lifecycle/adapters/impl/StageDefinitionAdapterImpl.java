package com.maukaim.bulo.definitions.data.lifecycle.adapters.impl;

import com.maukaim.bulo.definitions.data.ParameterDefinition;
import com.maukaim.bulo.definitions.data.StageInputDefinition;
import com.maukaim.bulo.definitions.data.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.StageInputDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.StageOutputDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.technical.TechnicalStageDefinitionDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TechnicalStageDefinitionAdapterImpl implements TechnicalStageDefinitionAdapter {
    private final ParameterDefinitionAdapter parameterDefinitionAdapter;
    private final StageInputDefinitionAdapter stageInputDefinitionAdapter;
    private final StageOutputDefinitionAdapter stageOutputDefinitionAdapter;

    public TechnicalStageDefinitionAdapterImpl(ParameterDefinitionAdapter parameterDefinitionAdapter,
                                               StageInputDefinitionAdapter stageInputDefinitionAdapter,
                                               StageOutputDefinitionAdapter stageOutputDefinitionAdapter) {
        this.parameterDefinitionAdapter = parameterDefinitionAdapter;
        this.stageInputDefinitionAdapter = stageInputDefinitionAdapter;
        this.stageOutputDefinitionAdapter = stageOutputDefinitionAdapter;
    }

    @Override
    public TechnicalStageDefinition adapte(TechnicalStageDefinitionDto dto) {
        return new TechnicalStageDefinition(
                dto.getId(),
                resolveInputs(dto.getInputsByName()),
                resolveOutputs(dto.getOutputsByName()),
                resolveParameters(dto.getParameters())
        );
    }

    private List<ParameterDefinition> resolveParameters(List<ParameterDefinitionDto> parameterDtos) {
        return parameterDtos == null ? List.of() : parameterDtos.stream()
                .map(this.parameterDefinitionAdapter::adapte)
                .collect(Collectors.toList());
    }

    private Map<String, StageInputDefinition> resolveInputs(Map<String, StageInputDefinitionDto> inputDtosByName) {
        return inputDtosByName == null ? Map.of() : inputDtosByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.stageInputDefinitionAdapter.adapte(entry.getValue())
                ));
    }

    private Map<String, StageOutputDefinition> resolveOutputs(Map<String, StageOutputDefinitionDto> outputsByName) {
        return outputsByName == null ? Map.of() : outputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.stageOutputDefinitionAdapter.adapte(entry.getValue())
                ));
    }
}
