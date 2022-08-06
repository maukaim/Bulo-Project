package com.maukaim.bulo.io.definitions;

import java.util.List;

public class TechnicalStageDefinitionData {
    private String technicalStageDefinitionId;
    private List<ParameterDefinitionData> parameters;

    public TechnicalStageDefinitionData(String technicalStageDefinitionId, List<ParameterDefinitionData> parameters) {
        this.technicalStageDefinitionId = technicalStageDefinitionId;
        this.parameters = parameters;
    }

    public String getId() {
        return technicalStageDefinitionId;
    }

    public List<ParameterDefinitionData> getParameters() {
        return parameters;
    }
}
