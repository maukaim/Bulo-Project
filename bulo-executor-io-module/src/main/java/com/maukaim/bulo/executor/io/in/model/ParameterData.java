package com.maukaim.bulo.executor.io.in.model;

public class ParameterData {
    private String value;
    private String name;
    private String valueType;
    private String additionalDetails;

    public ParameterData(String value, String name, String valueType, String additionalDetails) {
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
}
