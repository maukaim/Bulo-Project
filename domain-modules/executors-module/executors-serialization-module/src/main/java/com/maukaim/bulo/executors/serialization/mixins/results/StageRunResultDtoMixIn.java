package com.maukaim.bulo.executors.serialization.mixins.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.executors.out.model.StageRunStatusDto;

import java.util.Map;

public class StageRunResultDtoMixIn {
    @JsonCreator
    public StageRunResultDtoMixIn(@JsonProperty("stageRunId") String stageRunId,
                                  @JsonProperty("status") StageRunStatusDto status,
                                  @JsonProperty("outputsByName") Map<String, String> outputsByName) {
    }
}
