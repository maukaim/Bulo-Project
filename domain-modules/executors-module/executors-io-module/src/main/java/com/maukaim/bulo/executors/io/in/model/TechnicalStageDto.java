package com.maukaim.bulo.executors.io.in.model;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStageDto extends StageDto {
    private String definitionId;

    public TechnicalStageDto(String stageId, List<ParameterDto> parameters, String definitionId) {
        super(stageId, StageType.TECHNICAL, parameters);
        this.definitionId = definitionId;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    @Override
    public String toString() {
        return "TechnicalStageDto{" +
                "definitionId='" + definitionId + '\'' +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                ", stageId='" + stageId + '\'' +
                '}';
    }
}
