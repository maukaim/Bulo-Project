package com.maukaim.bulo.executors.core.marshalling.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.maukaim.bulo.api.data.types.Any;
import com.maukaim.bulo.api.data.types.any.AnyArray;
import com.maukaim.bulo.api.data.types.any.AnyBoolean;
import com.maukaim.bulo.api.data.types.any.AnyNumber;
import com.maukaim.bulo.api.data.types.any.AnyObject;
import com.maukaim.bulo.api.data.types.any.AnyString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnyTypeDeserializerTest {
    private AnyTypeDeserializer deserializer;
    private ObjectMapper objectMapper;
    private DeserializationContext deserializationContext = mock(DeserializationContext.class);
    private JsonParser jsonParser = mock(JsonParser.class);
    private JsonNode jsonNode = mock(JsonNode.class);

    @BeforeEach
    public void setup() throws IOException {
        deserializer = new AnyTypeDeserializer();
        objectMapper = new ObjectMapper();

        when(deserializationContext.readTree(jsonParser)).thenReturn(jsonNode);
    }

    @Test
    public void deserialize_whenJsonString_returnsAnyString() throws IOException {
        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.STRING);
        when(jsonNode.textValue()).thenReturn("test");

        Any<?> result = deserializer.deserialize(jsonParser, deserializationContext);
        assertTrue(result instanceof AnyString);
        assertEquals("test", ((AnyString) result).getValue());
    }

    @Test
    public void deserialize_whenJsonBoolean_returnsAnyString() throws IOException {
        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.BOOLEAN);
        when(jsonNode.booleanValue()).thenReturn(true);

        Any<?> result = deserializer.deserialize(jsonParser, deserializationContext);
        assertTrue(result instanceof AnyBoolean);
        assertEquals(true, ((AnyBoolean) result).getValue());
    }

    @Test
    public void deserialize_whenJsonNumber_returnsAnyString() throws IOException {
        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.NUMBER);
        when(jsonNode.numberValue()).thenReturn(5);

        Any<?> result = deserializer.deserialize(jsonParser, deserializationContext);
        assertTrue(result instanceof AnyNumber);
        assertEquals(5, ((AnyNumber) result).getValue());
    }

    @Test
    public void deserialize_whenJsonArray_returnsAnyArray() throws IOException {
        String json = "[\"test\", \"test2\"]";
        JsonNode arrayNode = objectMapper.readTree(json);
        when(deserializationContext.readTree(jsonParser)).thenReturn(arrayNode);

        Any<?> result = deserializer.deserialize(jsonParser, deserializationContext);
        assertTrue(result instanceof AnyArray);
        List<? extends Any<?>> anyList = ((AnyArray) result).getValue();
        assertEquals(2, anyList.size());
    }

    @Test
    public void deserialize_whenJsonObject_returnsAnyObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("key1", "value1");
        objectNode.put("key2", "value2");

        when(deserializationContext.readTree(jsonParser)).thenReturn(objectNode);

        Any<?> result = deserializer.deserialize(jsonParser, deserializationContext);
        assertTrue(result instanceof AnyObject);
        Map<String, ? extends Any<?>> anyMap = ((AnyObject) result).getValue();
        assertEquals(2, anyMap.size());
    }

    @Test
    public void deserialize_whenMissingNodeType_throwsRuntimeException() {
        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.MISSING);

        assertThrows(RuntimeException.class, () -> deserializer.deserialize(jsonParser, deserializationContext));
    }

    @Test
    public void deserialize_whenPOJONodeType_throwsRuntimeException() {
        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.POJO);

        assertThrows(RuntimeException.class, () -> deserializer.deserialize(jsonParser, deserializationContext));
    }

    @Test
    public void deserialize_whenNullNodeType_throwsRuntimeException() {
        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.NULL);

        assertThrows(RuntimeException.class, () -> deserializer.deserialize(jsonParser, deserializationContext));
    }

    @Test
    public void deserialize_whenBinaryNodeType_throwsRuntimeException() {
        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.NULL);

        assertThrows(RuntimeException.class, () -> deserializer.deserialize(jsonParser, deserializationContext));
    }
}
