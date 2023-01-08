package com.maukaim.bulo.runners.api.models;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;

public class ParameterDefinition {
    private String name;
    private ParameterType parameterType;
    private String hint;
    private String description;

    public ParameterDefinition(String name, ParameterType parameterType, String hint, String description) {
        this.name = name;
        this.parameterType = parameterType;
        this.hint = hint;
        this.description = description;
    }

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
        return getParameterType().isRequired();
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "key='" + name + '\'' +
                ", parameterType=" + parameterType +
                ", hint='" + hint + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
