package com.maukaim.bulo.flows.io.stage;

import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class StageDto {
    protected StageType stageType;
    protected List<ParameterDto> parameters;
    protected String stageId;
    protected String definitionId;

    public StageDto(String stageId, String definitionId, StageType stageType, List<ParameterDto> parameters) {
        this.stageId = stageId;
        this.stageType = stageType;
        this.parameters = parameters;
        this.definitionId =definitionId;
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

    public String getDefinitionId() {
        return definitionId;
    }
}
