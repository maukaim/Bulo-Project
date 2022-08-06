package com.maukaim.bulo.executor.io.in.model;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class StageData {
    protected StageType stageType;
    protected List<ParameterData> parameters;

    public StageData(StageType stageType, List<ParameterData> parameters) {
        this.stageType = stageType;
        this.parameters = parameters;
    }

    public StageType getStageType() {
        return stageType;
    }

    public List<ParameterData> getParameters() {
        return parameters;
    }
}
