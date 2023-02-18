package com.maukaim.bulo.triggers.serialization;

import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.io.triggers.events.BasicTriggerEvent;
import com.maukaim.bulo.triggers.serialization.mixins.TriggerEventMixIn;
import com.maukaim.bulo.triggers.serialization.mixins.TriggerIdMixIn;

import java.util.Map;

public class TriggersMixinsConfig {

    public static Map<Class<?>, Class<?>> TRIGGERS_JACKSON_MIXIN = Map.of(
            BasicTriggerEvent.class, TriggerEventMixIn.class,
            TriggerId.class, TriggerIdMixIn.class
    );
}
