package com.maukaim.bulo.executors.core.marshalling.jackson.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface JacksonSerializationResolver {
    void resolveSerialization(Object object, ObjectMapper objectMapper);
}
