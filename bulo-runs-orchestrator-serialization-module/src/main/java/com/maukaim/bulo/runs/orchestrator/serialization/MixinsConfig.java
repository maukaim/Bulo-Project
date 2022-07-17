package com.maukaim.bulo.runs.orchestrator.serialization;

import com.maukaim.bulo.runs.orchestrator.io.in.AcknowledgeRequestStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.io.in.RunCancelledStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.io.in.RunFailedStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.io.in.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.io.in.StartRunStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.serialization.mixins.AcknowledgeRequestStageRunEventMixIn;
import com.maukaim.bulo.runs.orchestrator.serialization.mixins.StandardStageRunEventMixin;

import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> SERIALIZATION_JACKSON_MIXIN = Map.of(
            AcknowledgeRequestStageRunEvent.class, AcknowledgeRequestStageRunEventMixIn.class,
            RunCancelledStageRunEvent.class, StandardStageRunEventMixin.class,
            RunFailedStageRunEvent.class, StandardStageRunEventMixin.class,
            RunSuccessfulStageRunEvent.class, StandardStageRunEventMixin.class,
            StartRunStageRunEvent.class, StandardStageRunEventMixin.class
    );
}
