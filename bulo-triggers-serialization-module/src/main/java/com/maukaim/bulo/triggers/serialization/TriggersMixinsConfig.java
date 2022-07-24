package com.maukaim.bulo.triggers.serialization;

import com.maukaim.bulo.triggers.io.TriggerId;
import com.maukaim.bulo.triggers.io.out.BasicTriggerEvent;
import com.maukaim.bulo.triggers.serialization.mixins.TriggerEventMixIn;
import com.maukaim.bulo.triggers.serialization.mixins.TriggerIdMixin;

import java.util.Map;

public class TriggersMixinsConfig {

    public static Map<Class<?>, Class<?>> TRIGGERS_JACKSON_MIXIN = Map.of(
            BasicTriggerEvent.class, TriggerEventMixIn.class,
            TriggerId.class, TriggerIdMixin.class
    );
}
