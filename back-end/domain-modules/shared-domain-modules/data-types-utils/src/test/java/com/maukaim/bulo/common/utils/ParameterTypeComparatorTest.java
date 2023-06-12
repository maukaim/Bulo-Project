package com.maukaim.bulo.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.api.data.types.DataTypeCategory;
import com.maukaim.bulo.api.data.types.NativeType;
import com.maukaim.bulo.api.data.types.parameters.ArrayParameterType;
import com.maukaim.bulo.api.data.types.parameters.BuloParameterType;
import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParameterTypeComparatorTest {

    private ParameterTypeComparator parameterTypeComparator;
    private NativeTypeComparator nativeTypeComparator;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        nativeTypeComparator = mock(NativeTypeComparator.class);
        ObjectMapper realObjectMapper = new ObjectMapper();
        objectMapper = Mockito.spy(realObjectMapper);
        parameterTypeComparator = new ParameterTypeComparator(nativeTypeComparator, objectMapper);
    }

    @Test
    void isValueValid_whenValueIsNullAndParameterTypeIsRequired_expectedFalse() {
        ParameterType parameterType = mock(ParameterType.class);
        when(parameterType.isRequired()).thenReturn(true);

        boolean isValid = parameterTypeComparator.isValueValid(null, parameterType);

        assertFalse(isValid);
    }

    @Test
    void isValueValid_whenValueIsNullAndParameterTypeIsNotRequired_expectedTrue() {
        ParameterType parameterType = mock(ParameterType.class);
        when(parameterType.isRequired()).thenReturn(false);

        boolean isValid = parameterTypeComparator.isValueValid(null, parameterType);

        assertTrue(isValid);
    }

    @Test
    void isValueValid_whenValueIsBlankAndParameterTypeIsRequired_expectedFalse() {
        ParameterType parameterType = mock(ParameterType.class);
        when(parameterType.isRequired()).thenReturn(true);

        boolean isValid = parameterTypeComparator.isValueValid("", parameterType);

        assertFalse(isValid);
    }

    @Test
    void isValueValid_whenValueIsBlankAndParameterTypeIsNotRequired_expectedTrue() {
        ParameterType parameterType = mock(ParameterType.class);
        when(parameterType.isRequired()).thenReturn(false);

        boolean isValid = parameterTypeComparator.isValueValid("", parameterType);

        assertTrue(isValid);
    }

    @Test
    void isValueValid_whenValueIsNonNullAndParameterTypeIsNative_expectedResultFromNativeTypeComparator() {
        String value = "testValue";
        ParameterType parameterType = mock(NativeType.class);
        when(parameterType.isRequired()).thenReturn(true);
        when(parameterType.getDataTypeCategory()).thenReturn(DataTypeCategory.NATIVE);
        when(nativeTypeComparator.isValueValid(any(), any())).thenReturn(true);

        boolean isValid = parameterTypeComparator.isValueValid(value, parameterType);

        assertTrue(isValid);
    }

    @Test
    void isValueValid_whenValueIsNonNullAndParameterTypeIsBulo_expectedResultFromBuloCheck() {
        String value = "{ \"name\": \"John\" }";
        ParameterType parameterType = mock(BuloParameterType.class);
        when(parameterType.isRequired()).thenReturn(true);
        when(parameterType.getDataTypeCategory()).thenReturn(DataTypeCategory.BULO);
        Map<String, ParameterType> fields = new HashMap<>();
        NativeType fieldType = mock(NativeType.class);
        when(fieldType.isRequired()).thenReturn(true);
        when(fieldType.getDataTypeCategory()).thenReturn(DataTypeCategory.NATIVE);
        fields.put("name", fieldType);
        when(((BuloParameterType) parameterType).getFields()).thenReturn(fields);
        when(nativeTypeComparator.isValueValid(any(), any())).thenReturn(true);

        boolean isValid = parameterTypeComparator.isValueValid(value, parameterType);

        assertTrue(isValid);
    }

    @Test
    void isValueValid_whenValueIsNonNullAndParameterTypeIsArray_expectedResultFromArrayCheck() {
        String value = "[ \"John\", \"Doe\" ]";
        ParameterType parameterType = mock(ArrayParameterType.class);
        when(parameterType.isRequired()).thenReturn(true);
        when(parameterType.getDataTypeCategory()).thenReturn(DataTypeCategory.ARRAY);
        NativeType contentType = mock(NativeType.class);
        when(contentType.isRequired()).thenReturn(true);
        when(contentType.getDataTypeCategory()).thenReturn(DataTypeCategory.NATIVE);
        when(((ArrayParameterType) parameterType).getContentType()).thenReturn(contentType);
        when(nativeTypeComparator.isValueValid(any(), any())).thenReturn(true);

        boolean isValid = parameterTypeComparator.isValueValid(value, parameterType);

        assertTrue(isValid);
    }

    @Test
    void isValueValid_whenParameterTypeIsNotRequiredAndValueIsNull_expectedResultFromArrayCheck() throws IOException {
        String value = "[ \"John\", \"Doe\" ]";
        ParameterType parameterType = mock(ArrayParameterType.class);
        when(parameterType.isRequired()).thenReturn(false);
        when(parameterType.getDataTypeCategory()).thenReturn(DataTypeCategory.ARRAY);
        NativeType contentType = mock(NativeType.class);
        when(contentType.isRequired()).thenReturn(true);
        when(contentType.getDataTypeCategory()).thenReturn(DataTypeCategory.NATIVE);
        when(((ArrayParameterType) parameterType).getContentType()).thenReturn(contentType);
        when(((ArrayParameterType) parameterType).getContentType()).thenReturn(contentType);
        when(nativeTypeComparator.isValueValid(any(), any())).thenReturn(true);
        JsonNode jsonNode = mock(JsonNode.class);
        doReturn(jsonNode).when(objectMapper).readTree(any(JsonParser.class));
        when(jsonNode.isNull()).thenReturn(true);

        boolean isValid = parameterTypeComparator.isValueValid(value, parameterType);

        assertTrue(isValid);
    }
}
