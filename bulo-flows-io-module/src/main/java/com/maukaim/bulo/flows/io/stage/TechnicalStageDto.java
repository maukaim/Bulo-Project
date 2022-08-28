package com.maukaim.bulo.flows.io.stage;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStageDto extends StageDto {
    private String definitionId;

    public TechnicalStageDto(String globalStageId, List<ParameterDto> parameters, String definitionId) {
        super(globalStageId, StageType.TECHNICAL, parameters);
        this.definitionId = definitionId;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    @Override
    public String toString() {
        return "TechnicalStageDto{" +
                "definitionId='" + definitionId + '\'' +
                ", globalStageId='" + stageId + '\'' +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
