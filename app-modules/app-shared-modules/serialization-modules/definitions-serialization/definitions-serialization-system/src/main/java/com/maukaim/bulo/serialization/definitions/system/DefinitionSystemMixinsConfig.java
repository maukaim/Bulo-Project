package com.maukaim.bulo.serialization.definitions.system;

import com.maukaim.bulo.io.definitions.system.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.serialization.definitions.client.DefinitionClientMixinsConfig;
import com.maukaim.bulo.serialization.definitions.system.mixins.ExecutorUpdateEventMixIn;
import com.maukaim.bulo.serialization.definitions.system.mixins.StageDefinitionEventMixIn;

import java.util.HashMap;
import java.util.Map;

public class DefinitionSystemMixinsConfig {

    private static Map<Class<?>, Class<?>> SYSTEM_EVENTS_DEFINITIONS_MIXINS = Map.of(
            StageDefinitionEvent.class, StageDefinitionEventMixIn.class,
            ExecutorUpdateEvent.class, ExecutorUpdateEventMixIn.class
    );

    public static Map<Class<?>, Class<?>> DEFINITIONS_SYSTEM_JACKSON_MIXIN = new HashMap<>() {{
        putAll(SYSTEM_EVENTS_DEFINITIONS_MIXINS);
        putAll(DefinitionClientMixinsConfig.DEFINITIONS_CLIENT_JACKSON_MIXIN);
    }};
}
