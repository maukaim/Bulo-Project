package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.client.models.StageDefinitionTypeDto;
import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.FsStageDto;

import java.util.Set;

public class StageDefinitionDtoMixIn {
    @JsonCreator
    public StageDefinitionDtoMixIn(@JsonProperty("definitionId") String technicalStageDefinitionId,
                                   @JsonProperty("stageDefinitionType") StageDefinitionTypeDto definitionType,
                                   @JsonProperty("functionalSubStages") Set<FsStageDto> functionalSubStages,
                                   @JsonProperty("outputProviders") Set<OutputProviderDto> outputProviders) {
    }
}
