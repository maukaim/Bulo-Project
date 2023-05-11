package com.maukaim.bulo.serialization.shared;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.serialization.shared.mixins.ContextStageIdMixIn;

import java.util.HashMap;
import java.util.Map;


public class CommonMixinsConfig {
    public static final Map<Class<?>, Class<?>> COMMON_SERIALIZATION_JACKSON_MIXINS = new HashMap<>() {{
        put(ContextStageId.class, ContextStageIdMixIn.class);
    }};
}
