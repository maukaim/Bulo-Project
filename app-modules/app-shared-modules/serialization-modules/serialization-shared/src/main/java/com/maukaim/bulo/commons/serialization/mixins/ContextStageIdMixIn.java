package com.maukaim.bulo.commons.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContextStageIdMixIn {
    @JsonCreator
    ContextStageIdMixIn(@JsonProperty("stageId") String stageId, @JsonProperty("marker") Integer marker){
    }
}
