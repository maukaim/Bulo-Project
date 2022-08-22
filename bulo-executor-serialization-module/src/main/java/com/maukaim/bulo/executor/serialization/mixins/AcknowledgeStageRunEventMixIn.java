package com.maukaim.bulo.executor.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class AcknowledgeStageRunEventMixIn {
    @JsonCreator
    public AcknowledgeStageRunEventMixIn(@JsonProperty("executorId") String executorId,
                                         @JsonProperty("stageRunId") String stageRunId,
                                         @JsonProperty("instant") Instant instant){}
}
