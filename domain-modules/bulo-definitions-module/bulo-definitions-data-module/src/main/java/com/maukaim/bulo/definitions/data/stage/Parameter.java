package com.maukaim.bulo.definitions.data.stage;

public class Parameter {
    private String value;
    private String name;
    private String valueType;

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

    @Override
    public String toString() {
        return "Parameter{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", valueType='" + valueType + '\'' +
                '}';
    }
}
