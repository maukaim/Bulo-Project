package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.ParameterDefinition;
import com.maukaim.bulo.executor.io.out.model.ParameterDefinitionDto;

public class ParameterDefinitionDtoAdapterImpl implements ParameterDefinitionDtoAdapter {
    @Override
    public ParameterDefinitionDto adapte(ParameterDefinition parameterDefinition) {
        return new ParameterDefinitionDto(
                parameterDefinition.getName(),
                parameterDefinition.getValueType(),
                parameterDefinition.getHint(),
                parameterDefinition.getDescription(),
                parameterDefinition.isRequired()
        );
    }
}
