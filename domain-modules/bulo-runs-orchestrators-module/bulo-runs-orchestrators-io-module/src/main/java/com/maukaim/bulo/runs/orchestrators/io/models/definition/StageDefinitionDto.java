package com.maukaim.bulo.runs.orchestrators.io.models.definition;


import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionTypeDto;
import com.maukaim.bulo.commons.io.instructions.models.functional.OutputProviderDto;

import java.util.Set;

public class StageDefinitionDto {
    private final String definitionId;
    private final StageDefinitionTypeDto stageDefinitionType;
    private final Set<FsStageDto> functionalSubStages;
    private final Set<OutputProviderDto> outputProviders;

    public StageDefinitionDto(String definitionId,
                              StageDefinitionTypeDto stageDefinitionType,
                              Set<FsStageDto> functionalSubStages,
                              Set<OutputProviderDto> outputProviders) {
        this.definitionId = definitionId;
        this.stageDefinitionType = stageDefinitionType;
        this.functionalSubStages = functionalSubStages;
        this.outputProviders = outputProviders;
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

    public Set<OutputProviderDto> getOutputProviders() {
        return outputProviders;
    }

    @Override
    public String toString() {
        return "StageDefinitionDto{" +
                "definitionId='" + definitionId + '\'' +
                ", stageDefinitionType=" + stageDefinitionType +
                ", functionalSubStages=" + functionalSubStages +
                ", outputProviders=" + outputProviders +
                '}';
    }
}
