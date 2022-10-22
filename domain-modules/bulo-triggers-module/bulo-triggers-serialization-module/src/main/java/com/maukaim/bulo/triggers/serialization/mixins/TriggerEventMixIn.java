package com.maukaim.bulo.triggers.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.time.Instant;
import java.util.Set;

public class TriggerEventMixIn {
    @JsonCreator
    public TriggerEventMixIn(@JsonProperty("flowId") String flowId,
                             @JsonProperty("flowStageIds") Set<ContextualizedStageId> contextualizedStageIds,
                             @JsonProperty("instant") Instant instant) {
    }
}
