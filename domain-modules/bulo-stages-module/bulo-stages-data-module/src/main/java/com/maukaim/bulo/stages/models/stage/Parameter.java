package com.maukaim.bulo.stages.models.stage;

public class Parameter {
    private String value;
    private String name;
    private String valueType;
    private String additionalDetails;

    public Parameter(String value, String name, String valueType, String additionalDetails) {
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
