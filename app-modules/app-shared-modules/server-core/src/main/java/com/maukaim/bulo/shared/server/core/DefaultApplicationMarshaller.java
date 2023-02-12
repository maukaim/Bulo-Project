package com.maukaim.bulo.shared.server.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultApplicationMarshaller implements ApplicationMarshaller {
    protected final ObjectMapper objectMapper;

    public DefaultApplicationMarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String marshall(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println("Marshalling problem...");
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T unmarshall(String marshall, Class<T> clazz) {
        try {
            return objectMapper.readValue(marshall, clazz);
        } catch (JsonProcessingException e) {
            System.out.println("Unmarshalling problem...");
            throw new RuntimeException(e);
        }
    }
}
