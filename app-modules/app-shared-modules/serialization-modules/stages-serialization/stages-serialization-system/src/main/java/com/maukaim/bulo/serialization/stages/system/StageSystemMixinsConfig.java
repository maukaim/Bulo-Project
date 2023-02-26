package com.maukaim.bulo.serialization.stages.system;


import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.serialization.stages.client.StageClientMixinsConfig;
import com.maukaim.bulo.serialization.stages.system.mixins.StageUpdateEventMixIn;

import java.util.HashMap;
import java.util.Map;

public class StageSystemMixinsConfig {

    public static Map<Class<?>, Class<?>> STAGES_EVENT_SERVICE_JACKSON_MIXIN = Map.of(
            StageUpdateEvent.class, StageUpdateEventMixIn.class
    );

    public static Map<Class<?>, Class<?>> STAGES_SERVICE_JACKSON_MIXIN = new HashMap<>() {{
        putAll(STAGES_EVENT_SERVICE_JACKSON_MIXIN);
        putAll(StageClientMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
    }};
}
