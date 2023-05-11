package com.maukaim.bulo.serialization.data.types;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.maukaim.bulo.io.data.types.ArrayIoTypeDto;
import com.maukaim.bulo.io.data.types.ArrayParameterTypeDto;
import com.maukaim.bulo.io.data.types.BuloIoTypeDto;
import com.maukaim.bulo.io.data.types.BuloParameterTypeDto;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.io.data.types.natives.NativeTypeDto;
import com.maukaim.bulo.serialization.data.types.mixins.ArrayIoTypeDtoMixIn;
import com.maukaim.bulo.serialization.data.types.mixins.ArrayParameterTypeDtoMixIn;
import com.maukaim.bulo.serialization.data.types.mixins.BuloIoTypeDtoMixIn;
import com.maukaim.bulo.serialization.data.types.mixins.BuloParameterTypeDtoMixIn;
import com.maukaim.bulo.serialization.data.types.mixins.IoTypeDtoMixIn;
import com.maukaim.bulo.serialization.data.types.mixins.NativeTypeDtoDeserializer;
import com.maukaim.bulo.serialization.data.types.mixins.ParameterTypeDtoMixIn;

import java.util.HashMap;
import java.util.Map;

public class DataTypeMixInsConfig {
    public static final Map<Class<?>, Class<?>> DATA_TYPES_COMMONS_MIXINS = Map.of(
            IoTypeDto.class, IoTypeDtoMixIn.class,
            ParameterTypeDto.class, ParameterTypeDtoMixIn.class,

            BuloParameterTypeDto.class, BuloParameterTypeDtoMixIn.class,
            BuloIoTypeDto.class, BuloIoTypeDtoMixIn.class,

            ArrayParameterTypeDto.class, ArrayParameterTypeDtoMixIn.class,
            ArrayIoTypeDto.class, ArrayIoTypeDtoMixIn.class
    );

    public static final Map<Class<?>, JsonDeserializer<?>> COMMON_DESERIALIZERS = new HashMap<>() {{
        put(NativeTypeDto.class, new NativeTypeDtoDeserializer());
    }};
}
