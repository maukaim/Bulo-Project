package com.maukaim.bulo.flows.io.definition;

import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

public class ParameterDefinitionDto implements ParameterDefinitionInterface {
    private String name;
    private String valueType;
    private Boolean required;

    public ParameterDefinitionDto(String name, String valueType, boolean required) {
        this.name = name;
        this.valueType = valueType;
        this.required = required;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getValueType() {
        return valueType;
    }

    public boolean isRequired() {
        return required;
    }

    @Override
    public String toString() {
        return "ParameterDefinitionDto{" +
                "name='" + name + '\'' +
                ", valueType='" + valueType + '\'' +
                ", required=" + required +
                '}';
    }
}
