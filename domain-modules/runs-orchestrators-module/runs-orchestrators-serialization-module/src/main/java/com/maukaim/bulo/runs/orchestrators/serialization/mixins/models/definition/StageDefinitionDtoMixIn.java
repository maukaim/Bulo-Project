package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageDefinitionTypeDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.FsStageDto;

import java.util.Set;

public class StageDefinitionDtoMixIn {
    @JsonCreator
    public StageDefinitionDtoMixIn(@JsonProperty("definitionId") String technicalStageDefinitionId,
                                   @JsonProperty("stageDefinitionType") StageDefinitionTypeDto definitionType,
                                   @JsonProperty("functionalSubStages") Set<FsStageDto> functionalSubStages,
                                   @JsonProperty("outputProviders") Set<OutputProviderDto> outputProviders) {
    }
}