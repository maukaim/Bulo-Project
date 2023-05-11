package com.maukaim.bulo.executors.core.marshalling.jackson.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.maukaim.bulo.api.data.types.Any;
import com.maukaim.bulo.executors.core.marshalling.jackson.AnyTypeDeserializer;
import com.maukaim.bulo.executors.core.marshalling.jackson.BuloJacksonAnnotationIntrospector;

public abstract class AbstractObjectMapperProvider implements ObjectMapperProvider {

    /**
     * Helper method. Provide a standard base for all objectMappers.
     *
     * @return the default module that all ObjectMapper should carry on.
     */
    protected SimpleModule getDefaultModule() {
        return new SimpleModule()
                .addDeserializer(Any.class, new AnyTypeDeserializer());
    }

    /**
     * Helper method. Apply common settings for basic needs.
     *
     * @param objectMapper to setup.
     */
    protected void applyDefaultSettings(ObjectMapper objectMapper) {
        objectMapper.setAnnotationIntrospector(new BuloJacksonAnnotationIntrospector());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
