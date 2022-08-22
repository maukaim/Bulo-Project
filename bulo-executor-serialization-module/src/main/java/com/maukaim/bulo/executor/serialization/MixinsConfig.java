package com.maukaim.bulo.executor.serialization;

import com.maukaim.bulo.executor.io.in.NeedStageRunEvent;
import com.maukaim.bulo.executor.io.in.StageUpdateEvent;
import com.maukaim.bulo.executor.io.out.*;
import com.maukaim.bulo.executor.serialization.mixins.*;

import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> EXECUTORS_SERVICE_JACKSON_MIXIN = Map.of(
            AcknowledgeStageRunEvent.class, AcknowledgeStageRunEventMixIn.class,
            RunCancelledStageRunEvent.class, StandardStageRunEventMixin.class,
            RunFailedStageRunEvent.class, StandardStageRunEventMixin.class,
            RunSuccessfulStageRunEvent.class, StandardStageRunEventMixin.class,
            StartRunStageRunEvent.class, StandardStageRunEventMixin.class,
            NeedStageRunEvent.class, NeedStageRunEventMixIn.class,
            StageDefinitionDeclarationEvent.class, StageDefinitionDeclarationEventMixIn.class,
            StageUpdateEvent.class, StageUpdateEventMixIn.class
    );
}
