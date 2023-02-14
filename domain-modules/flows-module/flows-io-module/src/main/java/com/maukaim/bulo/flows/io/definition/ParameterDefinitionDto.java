package com.maukaim.bulo.flows.io.definition;

import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

public class ParameterDefinitionDto implements ParameterDefinitionInterface {
    private String name;
    private ParameterTypeDto parameterType;

    public ParameterDefinitionDto(String name, ParameterTypeDto parameterType) {
        this.name = name;
        this.parameterType = parameterType;
    }

    @Override
    public String getName() {
        return name;
    }

    public ParameterTypeDto getParameterType() {
        return parameterType;
    }

    @Override
    public String toString() {
        return "ParameterDefinitionDto{" +
                "name='" + name + '\'' +
                ", parameterType=" + parameterType + '\'' +
                '}';
    }
}
