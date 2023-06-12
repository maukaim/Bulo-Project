package com.maukaim.bulo.executors.core.marshalling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.maukaim.bulo.api.data.types.Any;
import com.maukaim.bulo.api.data.types.any.AnyString;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonDeserializationResolver;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonSerializationResolver;
import com.maukaim.bulo.runners.api.ExecutionCancelledException;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuloRunnerMarshallerTest {
    private final ObjectMapper objectMapper = mock(ObjectMapper.class);
    private final JacksonSerializationResolver serializationResolver = mock(JacksonSerializationResolver.class);
    private final JacksonDeserializationResolver deserializationResolver = mock(JacksonDeserializationResolver.class);
    private final BuloRunnerMarshaller buloRunnerMarshaller = new BuloRunnerMarshaller(objectMapper, serializationResolver, deserializationResolver);
    private Object input = new Object();
    private Any<String> genericTypeUnmarshalled = new AnyString("hihi");


    @Test
    void marshall() throws JsonProcessingException {
        when(objectMapper.writeValueAsString(input)).thenReturn("test");
        String result = buloRunnerMarshaller.marshall(input);
        assertEquals("test", result);
        verify(objectMapper).writeValueAsString(input);
    }

    @Test
    void marshall_whenNeedToResolveSerialization() throws JsonProcessingException {
        when(objectMapper.writeValueAsString(input)).thenThrow(JsonProcessingException.class).thenReturn("test");
        String result = buloRunnerMarshaller.marshall(input);
        assertEquals("test", result);
        verify(serializationResolver).resolveSerialization(input, objectMapper);
        verify(objectMapper, times(2)).writeValueAsString(input);

    }

    @Test
    void marshall_whenNeedSerializationResolutionDoesNotWork_ThrowException() throws JsonProcessingException {
        when(objectMapper.writeValueAsString(input)).thenThrow(JsonProcessingException.class);
        assertThrows(ExecutionCancelledException.class, () -> buloRunnerMarshaller.marshall(input));

        verify(serializationResolver).resolveSerialization(input, objectMapper);
        verify(objectMapper, times(2)).writeValueAsString(input);
    }

    @Test
    void unmarshall_whenClazzIsAssignableFromAnyOrChild_thenTypeAny() throws JsonProcessingException {
        unmarshallAsGenericTypeSuccessSetup();
        Object result = buloRunnerMarshaller.unmarshall("test", AnyString.class);

        assertSame(genericTypeUnmarshalled, result);
        verify(objectMapper).readValue("test", Any.class);
    }

    @Test
    void unmarshall_whenClassIsNotAnyClass_thenUnmarshallWithJavaType() throws JsonProcessingException {
        unmarshallAsGenericTypeSuccessSetup();
        JavaType javaType = mock(JavaType.class);
        when(objectMapper.constructType(String.class)).thenReturn(javaType);
        when(objectMapper.readValue(anyString(), eq(javaType))).thenReturn(genericTypeUnmarshalled);

        Object result = buloRunnerMarshaller.unmarshall("test", String.class);

        assertSame(genericTypeUnmarshalled, result);
        verify(objectMapper).readValue("test", javaType);
    }

    @Test
    void unmarshall_whenClassIsNotAnyClassAndJavaTypeStrategyFails_thenUnmarshallAfterResolve() throws JsonProcessingException {
        unmarshallAsGenericTypeSuccessSetup();
        JavaType javaType = mock(JavaType.class);
        when(objectMapper.constructType(String.class)).thenReturn(javaType);
        when(objectMapper.copy()).thenReturn(objectMapper);
        when(objectMapper.readValue(anyString(), eq(javaType)))
                .thenThrow(RuntimeException.class)
                .thenReturn(genericTypeUnmarshalled);

        Object result = buloRunnerMarshaller.unmarshall("test", String.class);

        assertSame(genericTypeUnmarshalled, result);
        verify(objectMapper, times(2)).readValue("test", javaType);
        verify(deserializationResolver).resolveDeserialization("test", String.class, objectMapper);
    }

    @Test
    void unmarshall_whenClassIsNotAnyClassAndJavaTypeStrategyFailsEvenAfterResolve_thenThrow() throws JsonProcessingException {
        unmarshallAsGenericTypeSuccessSetup();
        JavaType javaType = mock(JavaType.class);
        when(objectMapper.constructType(String.class)).thenReturn(javaType);
        when(objectMapper.copy()).thenReturn(objectMapper);
        when(objectMapper.readValue(anyString(), eq(javaType)))
                .thenThrow(JsonProcessingException.class);

        assertThrows(ExecutionCancelledException.class, () -> buloRunnerMarshaller.unmarshall("test", String.class));
    }

    @Test
    void unmarshallAsGeneric_thenSuccess() throws JsonProcessingException {
        unmarshallAsGenericTypeSuccessSetup();
        Any<?> result = buloRunnerMarshaller.unmarshallAsGenericType("test");

        assertSame(genericTypeUnmarshalled, result);
    }

    @Test
    void unmarshallAsGeneric_whenCanUnmarshallAsAny_thenThrow() throws JsonProcessingException {
        when(objectMapper.readValue(anyString(), eq(Any.class))).thenThrow(JsonProcessingException.class);

        assertThrows(RuntimeException.class, () -> buloRunnerMarshaller.unmarshallAsGenericType("test"));
    }

    @Test
    void unmarshallAsCollection_whenInputIsAnArraythenSuccess() throws JsonProcessingException {
        TypeFactory typeFactory = mock(TypeFactory.class);
        CollectionType collectionType = mock(CollectionType.class);
        when(objectMapper.getTypeFactory()).thenReturn(typeFactory);
        when(typeFactory.constructCollectionType(List.class, String.class)).thenReturn(collectionType);
        JsonNode jsonNode = mock(JsonNode.class);
        when(objectMapper.readTree("[]")).thenReturn(jsonNode);
        when(jsonNode.isArray()).thenReturn(true);
        when(objectMapper.readValue("[]", collectionType)).thenReturn(List.of("hihi"));

        Collection<String> result = buloRunnerMarshaller.unmarshallAsCollection("[]", String.class);
        assertEquals(result, List.of("hihi"));
    }

    @Test
    void unmarshallAsCollection_whenJsonNodeImpossibleToRead_thenThrow() throws JsonProcessingException {
        TypeFactory typeFactory = mock(TypeFactory.class);
        CollectionType collectionType = mock(CollectionType.class);
        when(objectMapper.getTypeFactory()).thenReturn(typeFactory);
        when(typeFactory.constructCollectionType(List.class, String.class)).thenReturn(collectionType);
        when(objectMapper.readTree("[]")).thenThrow(JsonProcessingException.class);

        assertThrows(ExecutionCancelledException.class, () -> buloRunnerMarshaller.unmarshallAsCollection("[]", String.class));
    }

    @Test
    void unmarshallAsCollection_whenInputIsNotAnArray() throws JsonProcessingException {
        TypeFactory typeFactory = mock(TypeFactory.class);
        CollectionType collectionType = mock(CollectionType.class);
        when(objectMapper.getTypeFactory()).thenReturn(typeFactory);
        when(typeFactory.constructCollectionType(List.class, String.class)).thenReturn(collectionType);
        JsonNode jsonNode = mock(JsonNode.class);
        when(objectMapper.readTree("{}")).thenReturn(jsonNode);
        when(jsonNode.isArray()).thenReturn(false);
        JavaType javaType = mock(JavaType.class);
        when(objectMapper.constructType(String.class)).thenReturn(javaType);
        when(objectMapper.readValue(anyString(), eq(javaType))).thenReturn(genericTypeUnmarshalled);

        Collection<String> result = buloRunnerMarshaller.unmarshallAsCollection("{}", String.class);
        assertEquals(result, List.of(genericTypeUnmarshalled));
    }

    private void unmarshallAsGenericTypeSuccessSetup() throws JsonProcessingException {
        when(objectMapper.readValue(anyString(), eq(Any.class))).thenReturn(genericTypeUnmarshalled);
    }

}