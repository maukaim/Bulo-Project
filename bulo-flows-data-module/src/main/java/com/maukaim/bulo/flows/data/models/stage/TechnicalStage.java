package com.maukaim.bulo.flows.data.models.stage;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStage extends Stage {
    private final String definitionId;

    public TechnicalStage(String globalStageId, List<Parameter> parameters, String definitionId) {
        super(globalStageId, StageType.TECHNICAL, parameters);
        this.definitionId = definitionId;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    @Override
    public String toString() {
        return "TechnicalStage{" +
                "definitionId='" + definitionId + '\'' +
                ", stageId='" + stageId + '\'' +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
