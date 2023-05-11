package com.maukaim.bulo.serialization.triggers.system;

import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.io.triggers.system.events.BasicTriggerEvent;
import com.maukaim.bulo.serialization.triggers.system.mixins.TriggerIdMixIn;
import com.maukaim.bulo.serialization.triggers.system.mixins.TriggerEventMixIn;

import java.util.Map;

public class TriggersMixinsConfig {

    public static final Map<Class<?>, Class<?>> TRIGGERS_JACKSON_MIXIN = Map.of(
            BasicTriggerEvent.class, TriggerEventMixIn.class,
            TriggerId.class, TriggerIdMixIn.class
    );
}
