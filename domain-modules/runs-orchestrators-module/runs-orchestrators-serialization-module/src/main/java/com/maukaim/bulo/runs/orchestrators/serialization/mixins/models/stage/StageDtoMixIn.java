package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.stage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.StageType;

public class StageDtoMixIn {

    @JsonCreator
    public StageDtoMixIn(@JsonProperty("definitionId") String definitionId,
                         @JsonProperty("stageType") StageType stageType) {
    }

}
