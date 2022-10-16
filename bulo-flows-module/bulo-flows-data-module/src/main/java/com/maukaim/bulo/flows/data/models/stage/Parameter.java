package com.maukaim.bulo.flows.data.models.stage;

public class Parameter {
    private final String value;
    private final String name;
    private final String valueType;

    public Parameter(String value, String name, String valueType) {
        this.value = value;
        this.name = name;
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getValueType() {
        return valueType;
    }
}
