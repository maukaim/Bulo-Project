package com.maukaim.bulo.executors.serialization.mixins.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.executors.io.out.model.StageRunResultDto;
import com.maukaim.bulo.executors.io.out.model.StageRunStatusDto;

import java.time.Instant;
import java.util.Map;

public class StageRunResultDtoMixIn {
    @JsonCreator
    public StageRunResultDtoMixIn(@JsonProperty("stageRunId") String stageRunId,
                                  @JsonProperty("stageRunStatus") StageRunStatusDto status,
                                  @JsonProperty("outputsByName") Map<String, String> outputsByName) {
    }
}
