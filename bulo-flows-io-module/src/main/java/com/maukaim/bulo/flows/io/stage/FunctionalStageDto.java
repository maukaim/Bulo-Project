package com.maukaim.bulo.flows.io.stage;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class FunctionalStageDto extends StageDto {
    public FunctionalStageDto(List<ParameterDto> parameters) {
        super(StageType.FUNCTIONAL, parameters);
    }

    @Override
    public String toString() {
        return "FunctionalStageDto{" +
                "stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
