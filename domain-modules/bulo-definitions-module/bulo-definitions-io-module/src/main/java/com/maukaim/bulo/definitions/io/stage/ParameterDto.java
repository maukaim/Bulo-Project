package com.maukaim.bulo.definitions.io.stage;

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
}
