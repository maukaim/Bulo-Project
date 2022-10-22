package com.maukaim.bulo.definitions.data.stage;

import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class FunctionalStage extends Stage {
    public FunctionalStage(String stageId, List<Parameter> parameters, String definitionId) {
        super(stageId, definitionId, StageType.FUNCTIONAL, parameters);
    }

    @Override
    public String toString() {
        return "FunctionalStage{" +
                "stageId='" + stageId + '\'' +
                ", definitionId='" + definitionId + '\'' +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
