package com.maukaim.bulo.stages.ms.data.lifecycle.adapters.impl;

import com.maukaim.bulo.stages.io.models.definitions.TechnicalStageDefinitionDto;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;

import java.util.stream.Collectors;

public class TechnicalStageDefinitionAdapterImpl implements TechnicalStageDefinitionAdapter {
    private final ParameterDefinitionAdapter definitionAdapter;

    public TechnicalStageDefinitionAdapterImpl(ParameterDefinitionAdapter definitionAdapter) {
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public TechnicalStageDefinition adapte(TechnicalStageDefinitionDto dto) {
        return new TechnicalStageDefinition(
                dto.getId(),
                dto.getParameters().stream().map(definitionAdapter::adapte).collect(Collectors.toList())
        );
    }
}
