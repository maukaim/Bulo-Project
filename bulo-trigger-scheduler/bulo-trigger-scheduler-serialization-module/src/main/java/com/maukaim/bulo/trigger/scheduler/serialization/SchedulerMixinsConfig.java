package com.maukaim.bulo.trigger.scheduler.serialization;


import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConfig;
import com.maukaim.bulo.trigger.scheduler.serialization.mixins.ScheduleTriggerConfigMixIn;

import java.util.Map;

public class SchedulerMixinsConfig {

    public static Map<Class<?>, Class<?>> TRIGGER_SCHEDULER_JACKSON_MIXIN = Map.of(
            ScheduleTriggerConfig.class, ScheduleTriggerConfigMixIn.class
    );
}
