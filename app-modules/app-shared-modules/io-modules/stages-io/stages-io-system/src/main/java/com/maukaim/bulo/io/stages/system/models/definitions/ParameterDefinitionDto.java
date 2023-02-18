package com.maukaim.bulo.io.stages.system.models.definitions;

import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

public class ParameterDefinitionDto implements ParameterDefinitionInterface {
    private String name;
    private ParameterTypeDto parameterType;
    private String hint;
    private String description;

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
