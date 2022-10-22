package com.maukaim.bulo.stages.ms.data.lifecycle.adapters.impl;

import com.maukaim.bulo.stages.io.models.definitions.StageDefinitionDto;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;

import java.util.stream.Collectors;

public class TechnicalStageDefinitionAdapterImpl implements TechnicalStageDefinitionAdapter {
    private final ParameterDefinitionAdapter definitionAdapter;

    public TechnicalStageDefinitionAdapterImpl(ParameterDefinitionAdapter definitionAdapter) {
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public StageDefinition adapte(StageDefinitionDto dto) {
        return new StageDefinition(
                dto.getId(),
                dto.getParameters().stream().map(definitionAdapter::adapte).collect(Collectors.toList())
        );
    }
}
