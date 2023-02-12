package com.maukaim.bulo.mockingbird.marshaller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.shared.server.core.DefaultApplicationMarshaller;

import java.util.List;

public class MockingBirdMarshaller extends DefaultApplicationMarshaller {
    public MockingBirdMarshaller(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    public <T> List<T> unmarshallAsList(String marshall, Class<T> clazz) {
        JavaType javaType = asListOf(clazz);
        try {
            return objectMapper.readValue(marshall, javaType);
        } catch (Exception e) {
            System.out.println("Unmarshalling problem...");
            throw new RuntimeException(e);
        }
    }

    private JavaType asListOf(Class<?> clazz) {
        return objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
    }
}
