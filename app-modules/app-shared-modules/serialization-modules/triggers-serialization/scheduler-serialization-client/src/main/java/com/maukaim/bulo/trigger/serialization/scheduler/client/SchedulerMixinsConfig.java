package com.maukaim.bulo.trigger.serialization.scheduler.client;

import com.maukaim.bulo.io.scheduler.client.CreateScheduleTriggerInstruction;
import com.maukaim.bulo.trigger.serialization.scheduler.client.mixins.ScheduleTriggerConfigDtoMixIn;

import java.util.Map;

public class SchedulerMixinsConfig {

    public static Map<Class<?>, Class<?>> TRIGGER_SCHEDULER_JACKSON_MIXIN = Map.of(
            CreateScheduleTriggerInstruction.class, ScheduleTriggerConfigDtoMixIn.class
    );
}
