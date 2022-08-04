package com.maukaim.bulo.stages.models.stage;

import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class Stage {
    protected String stageId;
    protected StageType stageType;
    protected List<Parameter> parameters;

    public Stage(String globalStageId, StageType stageType, List<Parameter> parameters) {
        this.stageId = globalStageId;
        this.stageType = stageType;
        this.parameters = parameters;
    }

    public String getStageId() {
        return stageId;
    }

    public StageType getStageType() {
        return stageType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
}
