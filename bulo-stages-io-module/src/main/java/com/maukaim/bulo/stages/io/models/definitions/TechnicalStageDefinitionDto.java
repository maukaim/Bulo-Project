package com.maukaim.bulo.stages.io.models.definitions;

import java.util.List;

public class TechnicalStageDefinitionDto {
    private String technicalStageDefinitionId;
    private List<ParameterDefinitionDto> parameters;

    public TechnicalStageDefinitionDto(String technicalStageDefinitionId, List<ParameterDefinitionDto> parameters) {
        this.technicalStageDefinitionId = technicalStageDefinitionId;
        this.parameters = parameters;
    }

    public String getId() {
        return technicalStageDefinitionId;
    }

    public List<ParameterDefinitionDto> getParameters() {
        return parameters;
    }
}
