package com.maukaim.bulo.flows.data.models.definition;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

public class ParameterDefinition implements ParameterDefinitionInterface {
    private final String name;
    private final ParameterType parameterType;

    public ParameterDefinition(String name, ParameterType parameterType) {
        this.name = name;
        this.parameterType = parameterType;
    }

    @Override
    public String getName() {
        return name;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public boolean isRequired() {
        return parameterType.isRequired();
    }

    @Override
    public String toString() {
        return "ParameterDefinition{" +
                "name='" + name + '\'' +
                ", parameterType=" + parameterType +
                '}';
    }
}
