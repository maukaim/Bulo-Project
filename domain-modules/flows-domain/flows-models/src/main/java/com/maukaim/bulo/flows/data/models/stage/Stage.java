package com.maukaim.bulo.flows.data.models.stage;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class Stage {
    protected final String stageId;
    protected final StageType stageType;
    protected final List<Parameter> parameters;
    protected final String definitionId;

    public Stage(String stageId, String definitionId, StageType stageType, List<Parameter> parameters) {
        this.stageId = stageId;
        this.stageType = stageType;
        this.parameters = parameters;
        this.definitionId = definitionId;
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

    public String getDefinitionId() {
        return definitionId;
    }
}
