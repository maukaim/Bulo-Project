package com.maukaim.bulo.stages.core.definitions;

import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionData;
import com.maukaim.bulo.stages.core.ParameterDefinitionAdapter;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

import java.util.stream.Collectors;

public class TechnicalStageDefinitionAdapterImpl implements TechnicalStageDefinitionAdapter {
    private final ParameterDefinitionAdapter definitionAdapter;

    public TechnicalStageDefinitionAdapterImpl(ParameterDefinitionAdapter definitionAdapter) {
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public TechnicalStageDefinition adapte(TechnicalStageDefinitionData data) {
        return new TechnicalStageDefinition(
                data.getId(),
                data.getParameters().stream().map(definitionAdapter::adapte).collect(Collectors.toList())
        );
    }
}
