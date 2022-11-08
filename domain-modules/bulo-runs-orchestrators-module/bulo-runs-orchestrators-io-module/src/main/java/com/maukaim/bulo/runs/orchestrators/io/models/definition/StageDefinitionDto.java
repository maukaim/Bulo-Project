package com.maukaim.bulo.runs.orchestrators.io.models.definition;


import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionTypeDto;

import java.util.Set;

public class StageDefinitionDto {
    private final String definitionId;
    private final StageDefinitionTypeDto stageDefinitionType;
    private final Set<FsStageDto> functionalSubStages;

    public StageDefinitionDto(String definitionId,
                              StageDefinitionTypeDto stageDefinitionType,
                              Set<FsStageDto> functionalSubStages) {
        this.definitionId = definitionId;
        this.stageDefinitionType = stageDefinitionType;
        this.functionalSubStages = functionalSubStages;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public StageDefinitionTypeDto getStageDefinitionType() {
        return stageDefinitionType;
    }

    public Set<FsStageDto> getFunctionalSubStages() {
        return functionalSubStages;
    }

    @Override
    public String toString() {
        return "StageDefinitionDto{" +
                "definitionId='" + definitionId + '\'' +
                ", stageDefinitionType=" + stageDefinitionType +
                ", functionalSubStages=" + functionalSubStages +
                '}';
    }
}
