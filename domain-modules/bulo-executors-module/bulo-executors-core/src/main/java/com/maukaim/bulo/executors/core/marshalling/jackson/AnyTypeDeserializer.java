package com.maukaim.bulo.executors.core.marshalling.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.maukaim.bulo.api.data.types.Any;
import com.maukaim.bulo.api.data.types.any.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AnyTypeDeserializer extends JsonDeserializer<Any<?>> {
    private static final long serialVersionUID = 8571162321775154979L;

    @Override
    public Any<?> deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException, JacksonException {
        JsonNode jsonNode = ctx.readTree(jsonParser);
        return resolveAny(jsonNode);
    }

    private Any<?> resolveAny(JsonNode jsonNode) {
        return switch (jsonNode.getNodeType()) {
            case ARRAY -> new AnyArray(getList(((ArrayNode)jsonNode)));
            case BOOLEAN -> new AnyBoolean(jsonNode.booleanValue());
            case NUMBER -> new AnyNumber(jsonNode.numberValue());
            case OBJECT -> getObject((ObjectNode)jsonNode);
            case STRING -> new AnyString(jsonNode.textValue());
            case POJO, MISSING, NULL, BINARY -> throw new RuntimeException("Should never see it during deserialization process.");
        };
    }

    private AnyObject getObject(ObjectNode objectNode) {
        Spliterator<Map.Entry<String,JsonNode>>
                spliterator = Spliterators
                .spliteratorUnknownSize(objectNode.fields(), 0);

        Map<String, ? extends Any<?>> anyMap = StreamSupport.stream(spliterator, false)
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> resolveAny(entry.getValue())
                ));
        return new AnyObject(anyMap);
    }

    private List<? extends Any<?>> getList(ArrayNode arrayNode) {
        return StreamSupport.stream(arrayNode.spliterator(),false)
                .map(item -> resolveAny(item))
                .collect(Collectors.toList());
    }
}