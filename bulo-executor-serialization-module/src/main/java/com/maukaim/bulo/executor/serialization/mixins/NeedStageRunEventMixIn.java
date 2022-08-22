package com.maukaim.bulo.executor.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class NeedStageRunEventMixIn {
    @JsonCreator
    public NeedStageRunEventMixIn(@JsonProperty("stageId") String globalStageId,
                                  @JsonProperty("stageRunId") String stageRunId,
                                  @JsonProperty("ancestorsByInputName") Map<String, Map<String, Set<String>>> ancestorStageRunIdsByInputName,
                                  @JsonProperty("instant") Instant instant){

    }
}
