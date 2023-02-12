package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;

import java.time.Instant;
import java.util.Set;

public class TriggerEventMixIn {
    @JsonCreator
    public TriggerEventMixIn(@JsonProperty("flowId") String flowId,
                             @JsonProperty("flowStageIds") Set<ContextStageId> contextStageIds,
                             @JsonProperty("instant") Instant instant) {
    }
}
