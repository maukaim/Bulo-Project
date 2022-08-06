package com.maukaim.bulo.executor.io.out.model;

import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

public class ParameterDefinition implements ParameterDefinitionInterface {
    private String name;
    private String valueType;
    private String hint;
    private String description;
    private Boolean required;

    public ParameterDefinition(String name, String valueType, String hint, String description, boolean required) {
        this.name = name;
        this.valueType = valueType;
        this.hint = hint;
        this.description = description;
        this.required = required;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getValueType() {
        return valueType;
    }

    public String getHint() {
        return hint;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return required;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "key='" + name + '\'' +
                ", valueType=" + valueType +
                ", hint='" + hint + '\'' +
                ", description='" + description + '\'' +
                ", required=" + required +
                '}';
    }

}
