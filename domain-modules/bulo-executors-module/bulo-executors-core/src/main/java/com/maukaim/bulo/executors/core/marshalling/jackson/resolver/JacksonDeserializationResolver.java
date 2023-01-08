package com.maukaim.bulo.executors.core.marshalling.jackson.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface JacksonDeserializationResolver {
    void resolveDeserialization(String value, Class<?> clazz, ObjectMapper objectMapper);
}
