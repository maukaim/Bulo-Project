package com.maukaim.bulo.commons.serialization;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.maukaim.bulo.io.data.types.ArrayIoTypeDto;
import com.maukaim.bulo.io.data.types.ArrayParameterTypeDto;
import com.maukaim.bulo.io.data.types.BuloIoTypeDto;
import com.maukaim.bulo.io.data.types.BuloParameterTypeDto;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.io.data.types.natives.NativeTypeDto;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.commons.serialization.types.*;

import java.util.HashMap;
import java.util.Map;

public class CommonMixinsConfig {

    public static Map<Class<?>, Class<?>> DATA_TYPES_COMMONS_MIXINS = Map.of(
            IoTypeDto.class, IoTypeDtoMixIn.class,
            ParameterTypeDto.class, ParameterTypeDtoMixIn.class,

            BuloParameterTypeDto.class, BuloParameterTypeDtoMixIn.class,
            BuloIoTypeDto.class, BuloIoTypeDtoMixIn.class,

            ArrayParameterTypeDto.class, ArrayParameterTypeDtoMixIn.class,
            ArrayIoTypeDto.class, ArrayIoTypeDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> COMMON_SERIALIZATION_JACKSON_MIXINS = new HashMap<>() {{
        put(ContextStageId.class, ContextStageIdMixIn.class);
        putAll(DATA_TYPES_COMMONS_MIXINS);
    }};

    public static Map<Class<?>, JsonDeserializer<?>> COMMON_DESERIALIZERS = new HashMap<>() {{
        put(NativeTypeDto.class, new NativeTypeDtoDeserializer());
    }};
}
