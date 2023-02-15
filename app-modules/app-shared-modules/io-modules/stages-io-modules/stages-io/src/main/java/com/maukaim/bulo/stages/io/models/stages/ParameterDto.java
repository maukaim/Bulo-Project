package com.maukaim.bulo.stages.io.models.stages;

public class ParameterDto {
    private String value;
    private String name;
    private String additionalDetails;

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
