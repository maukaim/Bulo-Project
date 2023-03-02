package com.maukaim.bulo.flows.data.models.stage;

public class Parameter {
    private final String value;
    private final String name;

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
