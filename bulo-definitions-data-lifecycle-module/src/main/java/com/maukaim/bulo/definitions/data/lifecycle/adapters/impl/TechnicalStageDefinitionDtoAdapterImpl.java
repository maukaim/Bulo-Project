package com.maukaim.bulo.definitions.data.lifecycle.adapters.impl;

import com.maukaim.bulo.definitions.data.ParameterDefinition;
import com.maukaim.bulo.definitions.data.StageInputDefinition;
import com.maukaim.bulo.definitions.data.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.io.models.ParameterDefinitionDto;
import com.maukaim.bulo.definitions.io.models.StageInputDefinitionDto;
import com.maukaim.bulo.definitions.io.models.StageOutputDefinitionDto;
import com.maukaim.bulo.definitions.io.models.TechnicalStageDefinitionDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TechnicalStageDefinitionDtoAdapterImpl implements TechnicalStageDefinitionDtoAdapter {
    private final ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter;
    private final StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter;
    private final StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter;

    public TechnicalStageDefinitionDtoAdapterImpl(ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
                                                  StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
                                                  StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter) {
        this.parameterDefinitionDtoAdapter = parameterDefinitionDtoAdapter;
        this.stageInputDefinitionDtoAdapter = stageInputDefinitionDtoAdapter;
        this.stageOutputDefinitionDtoAdapter = stageOutputDefinitionDtoAdapter;
    }

    @Override
    public TechnicalStageDefinitionDto adapte(TechnicalStageDefinition definition) {
        return new TechnicalStageDefinitionDto(
                definition.getTechnicalStageDefinitionId(),
                resolveInputs(definition.getInputsByName()),
                resolveOutputs(definition.getOutputsByName()),
                resolveParameters(definition.getParameters())
        );
    }

    private List<ParameterDefinitionDto> resolveParameters(List<ParameterDefinition> parameter) {
        return parameter == null ? List.of() : parameter.stream()
                .map(this.parameterDefinitionDtoAdapter::adapte)
                .collect(Collectors.toList());
    }

    private Map<String, StageInputDefinitionDto> resolveInputs(Map<String, StageInputDefinition> inputsByName) {
        return inputsByName == null ? Map.of() : inputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.stageInputDefinitionDtoAdapter.adapte(entry.getValue())
                ));
    }

    private Map<String, StageOutputDefinitionDto> resolveOutputs(Map<String, StageOutputDefinition> outputsByName) {
        return outputsByName == null ? Map.of() : outputsByName.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.stageOutputDefinitionDtoAdapter.adapte(entry.getValue())
                ));
    }
}
