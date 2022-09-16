package com.maukaim.bulo.flows.io.stage;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStageDto extends StageDto {
    private final String definitionId;

    public TechnicalStageDto(List<ParameterDto> parameters, String definitionId) {
        super(StageType.TECHNICAL, parameters);
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
                '}';
    }
}
