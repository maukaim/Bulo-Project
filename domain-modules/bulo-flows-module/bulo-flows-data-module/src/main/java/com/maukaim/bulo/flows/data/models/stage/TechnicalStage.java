package com.maukaim.bulo.flows.data.models.stage;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStage extends Stage {

    public TechnicalStage(String stageId, List<Parameter> parameters, String definitionId) {
        super(stageId, definitionId, StageType.TECHNICAL, parameters);
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
