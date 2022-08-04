package com.maukaim.bulo.stages.models.stage;

import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class FunctionalStage extends Stage {

    public FunctionalStage(String globalStageId, List<Parameter> parameters) {
        super(globalStageId, StageType.FUNCTIONAL, parameters);
    }


    @Override
    public String toString() {
        return "FunctionalStage{" +
                "stageId='" + stageId + '\'' +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
