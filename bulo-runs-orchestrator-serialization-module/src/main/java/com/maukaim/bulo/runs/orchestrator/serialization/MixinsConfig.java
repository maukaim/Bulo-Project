package com.maukaim.bulo.runs.orchestrator.serialization;

import com.maukaim.bulo.runs.orchestrator.io.in.*;
import com.maukaim.bulo.runs.orchestrator.serialization.mixins.AcknowledgeRequestStageRunEventMixIn;
import com.maukaim.bulo.runs.orchestrator.serialization.mixins.StandardStageRunEventMixin;
import com.maukaim.bulo.runs.orchestrator.serialization.mixins.TriggerEventMixIn;

import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> SERIALIZATION_JACKSON_MIXIN = Map.of(
            AcknowledgeRequestStageRunEvent.class, AcknowledgeRequestStageRunEventMixIn.class,
            RunCancelledStageRunEvent.class, StandardStageRunEventMixin.class,
            RunFailedStageRunEvent.class, StandardStageRunEventMixin.class,
            RunSuccessfulStageRunEvent.class, StandardStageRunEventMixin.class,
            StartRunStageRunEvent.class, StandardStageRunEventMixin.class,
            BasicTriggerEvent.class, TriggerEventMixIn.class);
}
