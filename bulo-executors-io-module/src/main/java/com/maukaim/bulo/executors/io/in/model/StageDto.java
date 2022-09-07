package com.maukaim.bulo.executors.io.in.model;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class StageDto {
    protected StageType stageType;
    protected List<ParameterDto> parameters;

    public StageDto(StageType stageType, List<ParameterDto> parameters) {
        this.stageType = stageType;
        this.parameters = parameters;
    }


    public StageType getStageType() {
        return stageType;
    }

    public List<ParameterDto> getParameters() {
        return parameters;
    }
}
