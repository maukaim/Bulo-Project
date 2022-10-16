package com.maukaim.bulo.runners.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class SimpleJsonMarshaller implements Marshaller {
    private final ObjectMapper objectMapper;

    public SimpleJsonMarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String marshall(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T unmarshall(String marshall, Class<T> clazz) {
        try{
            return objectMapper.readValue(marshall, clazz);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Collection<T> unmarshallAsCollection(String marshall, Class<T> clazz) {
        CollectionType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);
        try {
            return objectMapper.readValue(marshall, javaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
