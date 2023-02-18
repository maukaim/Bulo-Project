package com.maukaim.bulo.io.stages.models.stages;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class FunctionalStageDto extends StageDto {
    public FunctionalStageDto(String stageId, List<ParameterDto> parameters, String definitionId) {
        super(stageId, definitionId, StageType.FUNCTIONAL, parameters);
    }

    @Override
    public String toString() {
        return "FunctionalStageData{" +
                "stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
