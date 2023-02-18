package com.maukaim.bulo.io.stages.models.definitions;

import java.util.List;

public class StageDefinitionDto {
    private String technicalStageDefinitionId;
    private List<ParameterDefinitionDto> parameters;

    public StageDefinitionDto(String technicalStageDefinitionId, List<ParameterDefinitionDto> parameters) {
        this.technicalStageDefinitionId = technicalStageDefinitionId;
        this.parameters = parameters;
    }

    public String getDefinitionId() {
        return technicalStageDefinitionId;
    }

    public List<ParameterDefinitionDto> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "TechnicalStageDefinitionDto{" +
                "technicalStageDefinitionId='" + technicalStageDefinitionId + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}