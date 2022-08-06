package com.maukaim.bulo.executor.io.in.model;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStageData extends StageData {
    private String definitionId;

    public TechnicalStageData(List<ParameterData> parameters, String definitionId) {
        super(StageType.TECHNICAL, parameters);
        this.definitionId = definitionId;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    @Override
    public String toString() {
        return "TechnicalStageData{" +
                "definitionId='" + definitionId + '\'' +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
