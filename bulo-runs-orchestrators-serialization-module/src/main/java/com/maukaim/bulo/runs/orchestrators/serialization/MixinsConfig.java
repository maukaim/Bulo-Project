package com.maukaim.bulo.runs.orchestrators.serialization;

import com.maukaim.bulo.runs.orchestrators.io.events.*;
import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunCancellationEvent;
import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunExecutionEvent;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.*;

import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> ORCHESTRATOR_SERIALIZATION_JACKSON_MIXIN = Map.of(
            AcknowledgeRequestStageRunEvent.class, AcknowledgeRequestStageRunEventMixIn.class,
            RunCancelledStageRunEvent.class, StandardStageRunEventMixin.class,
            RunFailedStageRunEvent.class, StandardStageRunEventMixin.class,
            RunSuccessfulStageRunEvent.class, StandardStageRunEventMixin.class,
            StartRunStageRunEvent.class, StandardStageRunEventMixin.class,
            BasicTriggerEvent.class, TriggerEventMixIn.class,
            NeedStageRunCancellationEvent.class, NeedStageRunCancellationEventMixIn.class,
            NeedStageRunExecutionEvent.class, NeedStageRunExecutionEventMixIn.class,
            FlowRunEvent.class, FlowRunEventMixIn.class
    );
}
