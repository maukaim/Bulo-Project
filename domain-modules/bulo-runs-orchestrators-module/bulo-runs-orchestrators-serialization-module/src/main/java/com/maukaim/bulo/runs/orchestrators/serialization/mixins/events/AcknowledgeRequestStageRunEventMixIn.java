package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class AcknowledgeRequestStageRunEventMixIn {
    @JsonCreator
    public AcknowledgeRequestStageRunEventMixIn(@JsonProperty("executorId") String executorId,
                                                @JsonProperty("stageRunId") String stageRunId,
                                                @JsonProperty("instant") Instant instant){}
}
