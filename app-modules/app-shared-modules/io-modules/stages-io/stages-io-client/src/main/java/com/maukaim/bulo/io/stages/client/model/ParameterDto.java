package com.maukaim.bulo.io.stages.client.model;

public class ParameterDto {
    private final String value;
    private final String name;
    private final String additionalDetails;

    public ParameterDto(String value, String name, String additionalDetails) {
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
}
