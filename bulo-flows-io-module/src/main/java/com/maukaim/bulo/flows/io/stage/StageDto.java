package com.maukaim.bulo.flows.io.stage;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class StageDto {
    protected String stageId;
    protected StageType stageType;
    protected List<ParameterDto> parameters;

    public StageDto(String stageId, StageType stageType, List<ParameterDto> parameters) {
        this.stageId = stageId;
        this.stageType = stageType;
        this.parameters = parameters;
    }

    public String getStageId() {
        return stageId;
    }

    public StageType getStageType() {
        return stageType;
    }

    public List<ParameterDto> getParameters() {
        return parameters;
    }
}
