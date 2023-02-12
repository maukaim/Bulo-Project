package com.maukaim.bulo.executors.data.stages;

public class Parameter {
    private String value;
    private String name;
    private String additionalDetails;

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
