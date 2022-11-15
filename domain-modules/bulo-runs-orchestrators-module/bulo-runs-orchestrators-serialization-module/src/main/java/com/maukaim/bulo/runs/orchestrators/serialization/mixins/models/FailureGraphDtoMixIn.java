package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FailedRunAlternativeRouteDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunStageDto;

import java.util.Set;

public class FailureGraphDtoMixIn {
    @JsonCreator
    public FailureGraphDtoMixIn(@JsonProperty("failedRunAlternativeRoutes") Set<FailedRunAlternativeRouteDto> failedRunAlternativeRoutes){}
}
