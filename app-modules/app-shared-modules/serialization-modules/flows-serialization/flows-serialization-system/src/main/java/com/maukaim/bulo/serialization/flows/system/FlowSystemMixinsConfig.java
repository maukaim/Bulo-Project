package com.maukaim.bulo.serialization.flows.system;


import com.maukaim.bulo.io.flows.system.FlowEvent;
import com.maukaim.bulo.serialization.flows.client.FlowClientMixinsConfig;
import com.maukaim.bulo.serialization.flows.system.mixins.FlowEventMixIn;

import java.util.HashMap;
import java.util.Map;

public class FlowSystemMixinsConfig {

    public static final Map<Class<?>, Class<?>> FLOW_EVENT_SERIALIZATION_JACKSON_MIXIN = Map.of(
            FlowEvent.class, FlowEventMixIn.class
    );

    public static final Map<Class<?>, Class<?>> FLOW_SERIALIZATION_JACKSON_MIXIN = new HashMap<>() {{
        putAll(FLOW_EVENT_SERIALIZATION_JACKSON_MIXIN);
        putAll(FlowClientMixinsConfig.FLOW_CLIENT_SERIALIZATION_JACKSON_MIXIN);
    }};
}
