package com.maukaim.bulo.definitions.data.definition;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

public class ParameterDefinition implements ParameterDefinitionInterface {
    private final String name;
    private final ParameterType parameterType;
    private final String hint;
    private final String description;

    public ParameterDefinition(String name, ParameterType parameterType, String hint, String description) {
        this.name = name;
        this.parameterType = parameterType;
        this.hint = hint;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public String getHint() {
        return hint;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return parameterType.isRequired();
    }

    @Override
    public String toString() {
        return "ParameterDefinition{" +
                "name='" + name + '\'' +
                ", parameterType=" + parameterType +
                ", hint='" + hint + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
