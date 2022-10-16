package com.maukaim.bulo.executors.io.in.model;

public class ParameterDto {
    private String value;
    private String name;
    private String valueType;
    private String additionalDetails;

    public ParameterDto(String value, String name, String valueType, String additionalDetails) {
        this.value = value;
        this.name = name;
        this.valueType = valueType;
        this.additionalDetails = additionalDetails;
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

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    @Override
    public String toString() {
        return "ParameterDto{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", valueType='" + valueType + '\'' +
                ", additionalDetails='" + additionalDetails + '\'' +
                '}';
    }
}
