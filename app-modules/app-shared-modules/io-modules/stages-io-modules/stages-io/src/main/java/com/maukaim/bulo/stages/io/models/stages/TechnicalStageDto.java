package com.maukaim.bulo.stages.io.models.stages;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStageDto extends StageDto {

    public TechnicalStageDto(String stageId, List<ParameterDto> parameters, String definitionId) {
        super(stageId, definitionId, StageType.TECHNICAL, parameters);
    }

    @Override
    public String toString() {
        return "TechnicalStageData{" +
                "definitionId='" + definitionId + '\'' +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
