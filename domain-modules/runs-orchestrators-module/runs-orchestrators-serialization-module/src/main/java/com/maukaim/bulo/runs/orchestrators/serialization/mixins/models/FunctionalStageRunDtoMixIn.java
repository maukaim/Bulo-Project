package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.io.runs.orchestrators.system.models.StageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.OrchestrableContextStatusDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.RunContextDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class FunctionalStageRunDtoMixIn {
    @JsonCreator
    public FunctionalStageRunDtoMixIn(@JsonProperty("contextId") String contextId,
                                      @JsonProperty("flowStageId") ContextStageId contextStageId,
                                      @JsonProperty("context") RunContextDto<?> context,
                                      @JsonProperty("status") OrchestrableContextStatusDto status,
                                      @JsonProperty("dependencies") Set<StageRunDependencyDto> dependencies,
                                      @JsonProperty("startTime") Instant startTime,
                                      @JsonProperty("endTime") Instant endTime,
                                      @JsonProperty("outputProviders") Set<OutputProviderDto> outputProviders,
                                      @JsonProperty("executionGraph") ExecutionGraphDto executionGraph,
                                      @JsonProperty("stageRunByIds")Map<String, StageRunDto<?>> stageRunByIds){}
}
