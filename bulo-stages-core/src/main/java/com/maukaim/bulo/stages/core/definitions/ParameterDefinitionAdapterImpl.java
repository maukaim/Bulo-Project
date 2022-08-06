package com.maukaim.bulo.stages.core.definitions;

import com.maukaim.bulo.io.definitions.ParameterDefinitionData;
import com.maukaim.bulo.stages.core.ParameterDefinitionAdapter;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;

public class ParameterDefinitionAdapterImpl implements ParameterDefinitionAdapter {
    @Override
    public ParameterDefinition adapte(ParameterDefinitionData data) {
        return new ParameterDefinition(
                data.getName(),
                data.getValueType(),
                data.getHint(),
                data.getDescription(),
                data.isRequired()
        );
    }
}
