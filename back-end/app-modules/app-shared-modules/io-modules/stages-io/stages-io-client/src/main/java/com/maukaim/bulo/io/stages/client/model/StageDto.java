package com.maukaim.bulo.io.stages.client.model;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public abstract class StageDto {
    protected final StageType stageType;
    protected final List<ParameterDto> parameters;
    protected final String stageId;
    protected final String definitionId;

    public StageDto(String stageId, String definitionId, StageType stageType, List<ParameterDto> parameters) {
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

    public List<ParameterDto> getParameters() {
        return parameters;
    }
}
