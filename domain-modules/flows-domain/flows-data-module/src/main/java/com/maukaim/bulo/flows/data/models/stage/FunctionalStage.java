package com.maukaim.bulo.flows.data.models.stage;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class FunctionalStage extends Stage {
    public FunctionalStage(String stageId, String definitionId, List<Parameter> parameters) {
        super(stageId, definitionId, StageType.FUNCTIONAL, parameters);
    }

    @Override
    public String toString() {
        return "FunctionalStage{" +
                "stageId='" + stageId + '\'' +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                ", definitionId='" + definitionId + '\'' +
                '}';
    }
}
