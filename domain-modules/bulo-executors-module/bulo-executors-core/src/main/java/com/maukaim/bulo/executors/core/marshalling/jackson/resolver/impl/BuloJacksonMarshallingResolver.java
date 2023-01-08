package com.maukaim.bulo.executors.core.marshalling.jackson.resolver.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonMarshallingResolver;
import com.maukaim.bulo.executors.core.marshalling.jackson.generator.MixInGeneratorStrategy;

import java.util.Map;


public class BuloJacksonMarshallingResolver implements JacksonMarshallingResolver {
    private final MixInGeneratorStrategy mixInGeneratorStrategy;

    public BuloJacksonMarshallingResolver(MixInGeneratorStrategy mixInGeneratorStrategy) {
        this.mixInGeneratorStrategy = mixInGeneratorStrategy;
    }

    @Override
    public void resolveDeserialization(String value, Class<?> clazz, ObjectMapper objectMapper) {
        Map<Class<?>, Class<?>> mixIns = this.mixInGeneratorStrategy.chainedGetMixIn(value, clazz);
        if (mixIns != null) {
            mixIns.forEach(objectMapper::addMixIn);
        }
    }

    @Override
    public void resolveSerialization(Object object, ObjectMapper objectMapper) {

    }
}
