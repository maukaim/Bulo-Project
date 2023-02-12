package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.runs.orchestrators.io.events.*;

@JsonTypeInfo(
        property = "eventType",
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RunCancelledStageRunEvent.class, name = "RUN_CANCELLED"),
        @JsonSubTypes.Type(value = AcknowledgeRequestStageRunEvent.class, name = "ACKNOWLEDGE_REQUEST"),
        @JsonSubTypes.Type(value = RunFailedStageRunEvent.class, name = "RUN_FAILED"),
        @JsonSubTypes.Type(value = StartRunStageRunEvent.class, name = "LAUNCH_RUN"),
        @JsonSubTypes.Type(value = RunSuccessfulStageRunEvent.class, name = "RUN_SUCCESSFUL"),
})
public class BasicStageRunEventMixIn {
}
