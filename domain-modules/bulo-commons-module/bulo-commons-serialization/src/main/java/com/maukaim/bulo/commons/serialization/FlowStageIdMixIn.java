package com.maukaim.bulo.commons.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlowStageIdMixIn {
    @JsonCreator
    FlowStageIdMixIn(@JsonProperty("stageId") String stageId, @JsonProperty("marker") Integer marker){
    }
}
