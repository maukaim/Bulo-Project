package com.maukaim.bulo.stages.io.models.stages;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class FunctionalStageDto extends StageDto {
    public FunctionalStageDto(List<ParameterDto> parameters) {
        super(StageType.FUNCTIONAL, parameters);
    }

    @Override
    public String toString() {
        return "FunctionalStageData{" +
                "stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
