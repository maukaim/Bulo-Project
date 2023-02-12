package com.maukaim.bulo.runs.orchestrators.io.models.stage;

import com.maukaim.bulo.commons.models.StageType;

public class StageDto {
    private final String definitionId;
    private final StageType stageType;

    public StageDto(String definitionId, StageType stageType) {
        this.definitionId = definitionId;
        this.stageType = stageType;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public StageType getStageType() {
        return stageType;
    }

    @Override
    public String toString() {
        return "StageDto{" +
                "definitionId='" + definitionId + '\'' +
                ", stageType=" + stageType +
                '}';
    }
}
