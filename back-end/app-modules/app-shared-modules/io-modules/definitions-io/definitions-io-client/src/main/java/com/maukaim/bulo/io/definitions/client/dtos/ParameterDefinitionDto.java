package com.maukaim.bulo.io.definitions.client.dtos;

import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

public class ParameterDefinitionDto implements ParameterDefinitionInterface {
    private final String name;
    private final ParameterTypeDto parameterType;
    private final String hint;
    private final String description;

    public ParameterDefinitionDto(String name, ParameterTypeDto parameterType, String hint, String description) {
        this.name = name;
        this.parameterType = parameterType;
        this.hint = hint;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public ParameterTypeDto getParameterType() {
        return parameterType;
    }

    public String getHint() {
        return hint;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ParameterDefinitionDto{" +
                "name='" + name + '\'' +
                ", parameterType='" + parameterType + '\'' +
                ", hint='" + hint + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
