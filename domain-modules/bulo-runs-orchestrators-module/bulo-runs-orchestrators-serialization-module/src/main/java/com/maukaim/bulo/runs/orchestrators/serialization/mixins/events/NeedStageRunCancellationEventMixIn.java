package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class NeedStageRunCancellationEventMixIn {
    @JsonCreator
    public NeedStageRunCancellationEventMixIn(@JsonProperty("stageRunId") String stageRunId,
                                              @JsonProperty("executorId") String executorId,
                                              @JsonProperty("instant") Instant instant) {
    }
}
