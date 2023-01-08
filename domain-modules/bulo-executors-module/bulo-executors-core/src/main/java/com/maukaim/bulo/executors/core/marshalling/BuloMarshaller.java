package com.maukaim.bulo.executors.core.marshalling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.maukaim.bulo.api.data.types.Any;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonDeserializationResolver;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonSerializationResolver;
import com.maukaim.bulo.runners.api.ExecutionCancelledException;
import com.maukaim.bulo.runners.api.Marshaller;

import java.util.Collection;
import java.util.List;

public class BuloMarshaller implements Marshaller {
    private final ObjectMapper objectMapper;
    private final JacksonSerializationResolver serializationResolver;
    private final JacksonDeserializationResolver deserializationResolver;

    public BuloMarshaller(ObjectMapper objectMapper,
                          JacksonSerializationResolver serializationResolver,
                          JacksonDeserializationResolver deserializationResolver) {
        this.objectMapper = objectMapper;
        this.serializationResolver = serializationResolver;
        this.deserializationResolver = deserializationResolver;
    }

    @Override
    public String marshall(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException exception1) {
            serializationResolver.resolveSerialization(obj, objectMapper);
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException exception2) {
                ExecutionCancelledException thrownException = ExecutionCancelledException.jsonDeserialization("Impossible to serialize object type:" + obj.getClass().getSimpleName());
                thrownException.addSuppressed(exception1);
                thrownException.addSuppressed(exception2);
                throw thrownException;
            }
        }
    }

    @Override
    public <T> T unmarshall(String marshall, Class<T> clazz) {
        if (clazz.isAssignableFrom(Any.class)) {
            return clazz.cast(this.unmarshallAsGenericType(marshall));
        }
        return unmarshall(marshall, objectMapper.constructType(clazz), clazz);
    }

    @Override
    public Any<?> unmarshallAsGenericType(String marshall) {
        try {
            return objectMapper.readValue(marshall, Any.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Collection<T> unmarshallAsCollection(String marshall, Class<T> clazz) {
        CollectionType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(marshall);
        } catch (JsonProcessingException e) {
            throw ExecutionCancelledException.jsonDeserialization(String.format(
                    "Impossible to read the provided value as a JSON: %s",
                    marshall));
        }

        if (!jsonNode.isArray()) {
            return List.of(this.unmarshall(marshall, clazz));
        }

        return unmarshall(marshall, javaType, clazz);
    }

    private <T> T unmarshall(String marshall, JavaType javaType, Class<?> basicClazz) {
        try {
            return objectMapper.readValue(marshall, javaType);
        } catch (Exception e) {
            deserializationResolver.resolveDeserialization(marshall, basicClazz, objectMapper);
            try {
                return objectMapper.copy().readValue(marshall, javaType);
            } catch (JsonProcessingException ex) {
                throw ExecutionCancelledException.jsonDeserialization(String.format(
                        "Impossible to deserialize Type %s from value: %s",
                        basicClazz.getSimpleName(),
                        marshall)
                );
            }
        }
    }
}
