package com.maukaim.bulo.trigger.scheduler.serialization;


import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConfigDto;
import com.maukaim.bulo.trigger.scheduler.serialization.mixins.ScheduleTriggerConfigDtoMixIn;

import java.util.Map;

public class SchedulerMixinsConfig {

    public static Map<Class<?>, Class<?>> TRIGGER_SCHEDULER_JACKSON_MIXIN = Map.of(
            ScheduleTriggerConfigDto.class, ScheduleTriggerConfigDtoMixIn.class
    );
}
