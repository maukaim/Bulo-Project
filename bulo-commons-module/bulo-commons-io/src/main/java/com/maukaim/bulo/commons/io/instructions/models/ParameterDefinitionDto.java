package com.maukaim.bulo.commons.io.instructions.models;

import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

public class ParameterDefinitionDto implements ParameterDefinitionInterface {
    private String name;
    private String valueType;
    private String hint;
    private String description;
    private Boolean required;

    public ParameterDefinitionDto(String name, String valueType, String hint, String description, boolean required) {
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
        return "ParameterDefinitionDto{" +
                "name='" + name + '\'' +
                ", valueType='" + valueType + '\'' +
                ", hint='" + hint + '\'' +
                ", description='" + description + '\'' +
                ", required=" + required +
                '}';
    }
}
