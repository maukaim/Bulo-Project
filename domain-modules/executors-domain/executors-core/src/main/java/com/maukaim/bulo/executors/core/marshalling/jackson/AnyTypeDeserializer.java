package com.maukaim.bulo.executors.core.marshalling.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.maukaim.bulo.api.data.types.Any;
import com.maukaim.bulo.api.data.types.any.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AnyTypeDeserializer extends JsonDeserializer<Any<?>> {

    @Override
    public Any<?> deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException, JacksonException {
        JsonNode jsonNode = ctx.readTree(jsonParser);
        return resolveAny(jsonNode);
    }

    private Any<?> resolveAny(JsonNode jsonNode) {
        return switch (jsonNode.getNodeType()) {
            case ARRAY -> new AnyArray(getList(((ArrayNode) jsonNode)));
            case BOOLEAN -> new AnyBoolean(jsonNode.booleanValue());
            case NUMBER -> new AnyNumber(jsonNode.numberValue());
            case OBJECT -> getObject((ObjectNode) jsonNode);
            case STRING -> new AnyString(jsonNode.textValue());
            case POJO, MISSING, NULL, BINARY ->
                    throw new RuntimeException("Should never see it during deserialization process.");
        };
    }

    private AnyObject getObject(ObjectNode objectNode) {
        Spliterator<Map.Entry<String, JsonNode>>
                spliterator = Spliterators
                .spliteratorUnknownSize(objectNode.fields(), 0);

        Map<String, ? extends Any<?>> anyMap = StreamSupport.stream(spliterator, false)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> resolveAny(entry.getValue())
                ));
        return new AnyObject(anyMap);
    }

    private List<? extends Any<?>> getList(ArrayNode arrayNode) {
        return StreamSupport.stream(arrayNode.spliterator(), false)
                .map(this::resolveAny)
                .collect(Collectors.toList());
    }
}