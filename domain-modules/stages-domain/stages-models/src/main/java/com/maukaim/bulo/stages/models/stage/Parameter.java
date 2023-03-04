package com.maukaim.bulo.stages.models.stage;

public class Parameter {
    private final String value;
    private final String name;
    private final String additionalDetails;

    public Parameter(String value, String name, String additionalDetails) {
        this.value = value;
        this.name = name;
        this.additionalDetails = additionalDetails;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", additionalDetails='" + additionalDetails + '\'' +
                '}';
    }
}
