package com.maukaim.bulo.definitions.io.stage;

public class ParameterDto {
    private String value;
    private String name;
    private String valueType;

    public ParameterDto(String value, String name, String valueType) {
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
