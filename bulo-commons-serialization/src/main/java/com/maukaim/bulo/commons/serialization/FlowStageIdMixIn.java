package com.maukaim.bulo.commons.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlowStageIdMixIn {
    @JsonCreator
    FlowStageIdMixIn(@JsonProperty("globalStageId") String globalStageId, @JsonProperty("marker") Integer marker){
    }
}
