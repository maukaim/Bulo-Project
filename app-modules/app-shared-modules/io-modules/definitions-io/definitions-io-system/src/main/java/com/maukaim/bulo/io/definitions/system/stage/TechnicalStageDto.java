package com.maukaim.bulo.io.definitions.system.stage;

import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStageDto extends StageDto {

    public TechnicalStageDto(String stageId, List<ParameterDto> parameters, String definitionId) {
        super(stageId, definitionId, StageType.TECHNICAL, parameters);
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
