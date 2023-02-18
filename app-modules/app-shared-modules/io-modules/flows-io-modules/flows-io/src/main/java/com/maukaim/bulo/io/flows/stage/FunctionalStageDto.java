package com.maukaim.bulo.io.flows.stage;

import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class FunctionalStageDto extends StageDto {
    public FunctionalStageDto(String stageId, String definitionId, List<ParameterDto> parameters) {
        super(stageId, definitionId, StageType.FUNCTIONAL, parameters);
    }

    @Override
    public String toString() {
        return "FunctionalStageDto{" +
                "stageType=" + stageType +
                ", parameters=" + parameters +
                ", stageId='" + stageId + '\'' +
                ", definitionId='" + definitionId + '\'' +
                '}';
    }
}
