package com.maukaim.bulo.io.executors.system.in.model;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class StageDto {
    protected StageType stageType;
    protected List<ParameterDto> parameters;
    protected String stageId;

    public StageDto(String stageId, StageType stageType, List<ParameterDto> parameters) {
        this.stageId =stageId;
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
