package com.maukaim.bulo.triggers.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Set;

public class TriggerIdMixIn {

    @JsonCreator
    public TriggerIdMixIn(@JsonProperty("flowId") String flowId,
                          @JsonProperty("flowStageIds") Set<ContextStageId> contextStageIds){}
}
