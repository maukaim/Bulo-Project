package com.maukaim.bulo.definitions.data.stage;

public class Parameter {
    private String value;
    private String name;
    private String valueType;

    public Parameter(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
