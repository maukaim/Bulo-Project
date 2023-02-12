package com.maukaim.bulo.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.maukaim.bulo.api.data.types.DataType;
import com.maukaim.bulo.api.data.types.NativeType;
import com.maukaim.bulo.api.data.types.parameters.ArrayParameterType;
import com.maukaim.bulo.api.data.types.parameters.BuloParameterType;
import com.maukaim.bulo.api.data.types.parameters.ParameterType;

import java.io.IOException;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static com.maukaim.bulo.api.data.types.DataTypeCategory.ARRAY;

public class ParameterTypeComparator {

    public static boolean isValueValid(String value, ParameterType parameterType) {
        if (!parameterType.isRequired() && (value == null || value.isBlank())) {
            return true;
        } else if (value == null) {
            return false;
        } else {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = mapper.getFactory();
            JsonNode jsonNode;
            try {
                JsonParser parser = factory.createParser(value);
                jsonNode = mapper.readTree(parser);
            } catch (IOException e) {
                jsonNode = new TextNode(value);
            }

            return switch (parameterType.getDataTypeCategory()) {
                case NATIVE -> NativeTypeComparator.isValueValid(jsonNode, (NativeType) parameterType);
                case BULO -> isValueValid(jsonNode, (BuloParameterType) parameterType);
                case ARRAY -> isValueValid(jsonNode, (ArrayParameterType) parameterType);
            };
        }
    }

    private static boolean isValueValid(JsonNode value, ArrayParameterType parameterType) {
        if (!parameterType.isRequired() && (value == null || value.isNull() || value.isEmpty())) {
            return true;
        }
        if (!value.isArray()) {
            return false;
        }
        ArrayNode arrayNode = (ArrayNode) value;
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                arrayNode.elements(),
                                Spliterator.ORDERED), false)
                .allMatch(jsonNode -> isValueValid(jsonNode, parameterType.getContentType()));
    }

    private static boolean isValueValid(JsonNode value, BuloParameterType parameterType) {
        if (!parameterType.isRequired() && (value == null || value.isNull() || value.isEmpty())) {
            return true;
        }
        return parameterType.getFields().entrySet().stream()
                .allMatch(entry -> {
                    String fieldName = entry.getKey();
                    ParameterType fieldType = entry.getValue();
                    JsonNode fieldValue = value.get(fieldName);
                    return isValueValid(fieldValue, fieldType);
                });
    }

    private static boolean isValueValid(JsonNode value, ParameterType parameterType) {
        if (!parameterType.isRequired() && (value == null || value.isNull() || value.isEmpty())) {
            return true;
        } else if (value == null || value.isNull()) {
            return false;
        } else {
            return switch (parameterType.getDataTypeCategory()) {
                case NATIVE -> NativeTypeComparator.isValueValid(value, (NativeType) parameterType);
                case BULO -> isValueValid(value, (BuloParameterType) parameterType);
                case ARRAY -> isValueValid(value, (ArrayParameterType) parameterType);
            };
        }
    }

    public static boolean areEquals(ParameterType type1, ParameterType type2) {
        if (sameInMemory(type1, type2)) {
            return true;
        }

        if (onlyOneIsNull(type1, type2)) {
            return false;
        }

        if (subTypesAreDifferent(type1, type2)) {
            return false;
        }

        return switch (type1.getDataTypeCategory()) {
            case NATIVE -> type1.getClass().equals(type2.getClass()) && type1.isRequired() == type2.isRequired();
            case BULO -> areEquals((BuloParameterType) type1, (BuloParameterType) type2);
            case ARRAY -> ARRAY.equals(type2.getDataTypeCategory()) &&
                    areEquals((ArrayParameterType) type1, (ArrayParameterType) type2);
        };
    }

    private static boolean areEquals(ArrayParameterType type1, ArrayParameterType type2) {
        if (type1.isRequired() != type2.isRequired()) {
            return false;
        }
        return areEquals(type1.getContentType(), type2.getContentType());
    }

    private static boolean areEquals(BuloParameterType type1, BuloParameterType type2) {
        if (type1.isRequired() != type2.isRequired()) {
            return false;
        }
        return areEquals(type1.getFields(), type2.getFields());
    }

    private static boolean areEquals(Map<String, ParameterType> fieldsType1, Map<String, ParameterType> fieldsType2) {
        if (!fieldsType1.keySet().equals(fieldsType2.keySet())) {
            return false;
        } else {
            return fieldsType1.entrySet().stream().allMatch(entry -> {
                        ParameterType fields1 = entry.getValue();
                        ParameterType fields2 = fieldsType2.get(entry.getKey());
                        return areEquals(fields1, fields2);
                    }
            );
        }
    }

    private static boolean subTypesAreDifferent(ParameterType type1, ParameterType type2) {
        return type1.getDataTypeCategory() != type2.getDataTypeCategory();
    }

    private static boolean onlyOneIsNull(DataType type1, ParameterType type2) {
        return (type1 == null && type2 != null) ||
                (type1 != null && type2 == null);
    }

    private static boolean sameInMemory(ParameterType type1, ParameterType type2) {
        return type1 == type2;
    }
}
