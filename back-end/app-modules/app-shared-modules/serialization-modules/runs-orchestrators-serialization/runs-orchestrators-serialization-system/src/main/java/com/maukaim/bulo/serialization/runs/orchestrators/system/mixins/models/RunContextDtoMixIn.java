package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.FlowRunContextDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.FunctionalStageRunContextDto;

@JsonTypeInfo(
        property = "contextType",
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FlowRunContextDto.class, name = "FLOW_RUN"),
        @JsonSubTypes.Type(value = FunctionalStageRunContextDto.class, name = "FUNCTIONAL_STAGE_RUN")
})
public class RunContextDtoMixIn {
}
