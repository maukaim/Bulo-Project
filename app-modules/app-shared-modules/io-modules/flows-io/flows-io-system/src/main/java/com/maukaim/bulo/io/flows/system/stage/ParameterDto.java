package com.maukaim.bulo.io.flows.system.stage;

public class ParameterDto {
    private String value;
    private String name;

    public ParameterDto(String value, String name) {
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
        return "ParameterDto{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
