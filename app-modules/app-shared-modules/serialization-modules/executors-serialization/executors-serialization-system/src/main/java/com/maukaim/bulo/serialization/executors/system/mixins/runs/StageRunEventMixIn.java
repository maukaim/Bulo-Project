package com.maukaim.bulo.serialization.executors.system.mixins.runs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.executors.system.AcknowledgeStageRunEvent;
import com.maukaim.bulo.io.executors.system.RunCancelledStageRunEvent;
import com.maukaim.bulo.io.executors.system.RunFailedStageRunEvent;
import com.maukaim.bulo.io.executors.system.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.io.executors.system.StartRunStageRunEvent;

@JsonTypeInfo(
        property = "eventType",
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RunCancelledStageRunEvent.class, name = "RUN_CANCELLED"),
        @JsonSubTypes.Type(value = AcknowledgeStageRunEvent.class, name = "ACKNOWLEDGE_REQUEST"),
        @JsonSubTypes.Type(value = RunFailedStageRunEvent.class, name = "RUN_FAILED"),
        @JsonSubTypes.Type(value = StartRunStageRunEvent.class, name = "LAUNCH_RUN"),
        @JsonSubTypes.Type(value = RunSuccessfulStageRunEvent.class, name = "RUN_SUCCESSFUL"),
})
public class StageRunEventMixIn {
}
