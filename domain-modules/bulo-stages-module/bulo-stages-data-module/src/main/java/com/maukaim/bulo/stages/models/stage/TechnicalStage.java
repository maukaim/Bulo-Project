package com.maukaim.bulo.stages.models.stage;

import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStage extends Stage {
    private String definitionId;
    private StageScope stageScope;

    public TechnicalStage(String stageId, List<Parameter> parameters, String definitionId, StageScope stageScope) {
        super(stageId, StageType.TECHNICAL, parameters);
        this.definitionId = definitionId;
        this.stageScope = stageScope;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public StageScope getStageScope() {
        return stageScope;
    }

    @Override
    public String toString() {
        return "TechnicalStage{" +
                "definitionId='" + definitionId + '\'' +
                ", stageScope=" + stageScope +
                ", stageId='" + stageId + '\'' +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
