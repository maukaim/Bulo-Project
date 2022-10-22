package com.maukaim.bulo.definitions.data.stage;

import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class Stage {
    protected String stageId;
    protected String definitionId;
    protected StageType stageType;
    protected List<Parameter> parameters;

    public Stage(String stageId, String definitionId, StageType stageType, List<Parameter> parameters) {
        this.stageId = stageId;
        this.stageType = stageType;
        this.parameters = parameters;
        this.definitionId = definitionId;
    }

    public String getDefinitionId() {
        return definitionId;
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
