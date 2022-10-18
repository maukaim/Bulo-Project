package com.maukaim.bulo.stages.io.models.stages;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class TechnicalStageDto extends StageDto {
    private String definitionId;
    private StageScopeDto stageScope;

    public TechnicalStageDto(String stageId, List<ParameterDto> parameters, String definitionId, StageScopeDto stageScope) {
        super(stageId, StageType.TECHNICAL, parameters);
        this.definitionId = definitionId;
        this.stageScope = stageScope;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public StageScopeDto getStageScope() {
        return stageScope;
    }

    @Override
    public String toString() {
        return "TechnicalStageDto{" +
                "definitionId='" + definitionId + '\'' +
                ", stageScope=" + stageScope +
                ", stageType=" + stageType +
                ", parameters=" + parameters +
                ", stageId='" + stageId + '\'' +
                '}';
    }
}
