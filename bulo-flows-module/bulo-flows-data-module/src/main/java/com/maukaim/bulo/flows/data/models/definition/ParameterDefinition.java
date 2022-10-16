package com.maukaim.bulo.flows.data.models.definition;

import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

public class ParameterDefinition implements ParameterDefinitionInterface {
    private String name;
    private String valueType;
    private Boolean required;

    public ParameterDefinition(String name, String valueType, boolean required) {
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
        return "ParameterDefinition{" +
                "name='" + name + '\'' +
                ", valueType='" + valueType + '\'' +
                ", required=" + required +
                '}';
    }
}
