package com.maukaim.bulo.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.api.data.types.NativeType;
import com.maukaim.bulo.api.data.types.natives.NativeTypeCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NativeTypeComparatorTest {

    private static ObjectMapper objectMapper;
    private static NativeTypeComparator nativeTypeComparator;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        nativeTypeComparator = new NativeTypeComparator();
    }

    @Test
    public void isValueValid_whenValueIsNullAndNativeTypeIsNotRequired_returnsTrue() {
        JsonNode value = null;
        NativeType nativeType = mock(NativeType.class);

        when(nativeType.isRequired()).thenReturn(false);

        assertTrue(nativeTypeComparator.isValueValid(value, nativeType));
    }

    @Test
    public void isValueValid_whenValueIsNullAndNativeTypeIsRequired_returnsFalse() {
        JsonNode value = null;
        NativeType nativeType = mock(NativeType.class);

        when(nativeType.isRequired()).thenReturn(true);

        assertFalse(nativeTypeComparator.isValueValid(value, nativeType));
    }

    @Test
    public void isValueValid_whenValueTypeMatchesNativeType_returnsTrue() {
        NativeType nativeType = mock(NativeType.class);
        when(nativeType.getNativeTypeCategory()).thenReturn(NativeTypeCategory.STRING);
        JsonNode value = objectMapper.valueToTree("test string");

        assertTrue(nativeTypeComparator.isValueValid(value, nativeType));

        when(nativeType.getNativeTypeCategory()).thenReturn(NativeTypeCategory.BOOLEAN);
        value = objectMapper.valueToTree(true);

        assertTrue(nativeTypeComparator.isValueValid(value, nativeType));

        when(nativeType.getNativeTypeCategory()).thenReturn(NativeTypeCategory.NUMBER);
        value = objectMapper.valueToTree(10);

        assertTrue(nativeTypeComparator.isValueValid(value, nativeType));
    }

    @Test
    public void isValueValid_whenValueTypeDoesNotMatchNativeType_returnsFalse() {
        NativeType nativeType = mock(NativeType.class);
        when(nativeType.getNativeTypeCategory()).thenReturn(NativeTypeCategory.STRING);
        JsonNode value = objectMapper.valueToTree(10);

        assertFalse(nativeTypeComparator.isValueValid(value, nativeType));

        when(nativeType.getNativeTypeCategory()).thenReturn(NativeTypeCategory.BOOLEAN);
        value = objectMapper.valueToTree("test string");

        assertFalse(nativeTypeComparator.isValueValid(value, nativeType));

        when(nativeType.getNativeTypeCategory()).thenReturn(NativeTypeCategory.NUMBER);
        value = objectMapper.valueToTree(true);

        assertFalse(nativeTypeComparator.isValueValid(value, nativeType));
    }
}