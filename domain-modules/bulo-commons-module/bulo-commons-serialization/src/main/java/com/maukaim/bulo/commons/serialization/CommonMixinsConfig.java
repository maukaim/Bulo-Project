package com.maukaim.bulo.commons.serialization;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Map;

public class CommonMixinsConfig {

    public static Map<Class<?>, Class<?>> COMMON_SERIALIZATION_JACKSON_MIXIN = Map.of(
            ContextualizedStageId.class, FlowStageIdMixIn.class
    );
}
