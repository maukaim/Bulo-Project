package com.maukaim.bulo.common.utils;

import com.maukaim.bulo.api.data.types.DataTypeCategory;
import com.maukaim.bulo.api.data.types.io.ArrayIoType;
import com.maukaim.bulo.api.data.types.io.BuloIoType;
import com.maukaim.bulo.api.data.types.io.IoType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.maukaim.bulo.api.data.types.DataTypeCategory.ARRAY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IoTypeComparatorTest {
    private IoTypeComparator comparator;
    private IoType type1;
    private IoType type2;

    @BeforeEach
    public void setup() {
        comparator = new IoTypeComparator();
        type1 = mock(IoType.class);
        type2 = mock(IoType.class);
    }

    @AfterEach
    public void tearDown() {
        comparator = null;
        type1 = null;
        type2 = null;
    }

    @Test
    public void areEquals_whenBothTypesAreNull_returnsTrue() {
        assertTrue(comparator.areEquals(null, null, true));
    }

    @Test
    public void areEquals_whenOnlyType1IsNull_returnsFalse() {
        when(type2.getDataTypeCategory()).thenReturn(ARRAY);
        assertFalse(comparator.areEquals(null, type2, true));
    }

    @Test
    public void areEquals_whenOnlyType2IsNull_returnsFalse() {
        when(type1.getDataTypeCategory()).thenReturn(ARRAY);
        assertFalse(comparator.areEquals(type1, null, true));
    }

    @Test
    public void areEquals_whenBothTypesAreSameInstance_returnsTrue() {
        assertTrue(comparator.areEquals(type1, type1, true));
    }

    @Test
    public void areEquals_whenDifferentCategories_returnsFalse() {
        when(type1.getDataTypeCategory()).thenReturn(DataTypeCategory.ARRAY);
        when(type2.getDataTypeCategory()).thenReturn(DataTypeCategory.BULO);
        assertFalse(comparator.areEquals(type1, type2, true));
    }

    @Test
    public void areEquals_whenDifferentClasses_returnsFalse() {
        ArrayIoType arrayType1 = mock(ArrayIoType.class);
        BuloIoType buloType2 = mock(BuloIoType.class);
        when(arrayType1.getDataTypeCategory()).thenReturn(DataTypeCategory.NATIVE);
        when(buloType2.getDataTypeCategory()).thenReturn(DataTypeCategory.NATIVE);
        assertFalse(comparator.areEquals(arrayType1, buloType2, true));
    }

    @Test
    public void areEquals_whenSameClassesDifferentIsRequired_returnsBasedOnSkip() {
        ArrayIoType arrayType1 = mock(ArrayIoType.class);
        ArrayIoType arrayType2 = mock(ArrayIoType.class);
        when(arrayType1.getDataTypeCategory()).thenReturn(DataTypeCategory.NATIVE);
        when(arrayType1.isRequired()).thenReturn(true);
        when(arrayType2.getDataTypeCategory()).thenReturn(DataTypeCategory.NATIVE);
        when(arrayType2.isRequired()).thenReturn(false);

        // skipIsRequiredCheck is true, so isRequired is not considered, should return true
        assertTrue(comparator.areEquals(arrayType1, arrayType2, true));

        // skipIsRequiredCheck is false, so different isRequired values cause return false
        assertFalse(comparator.areEquals(arrayType1, arrayType2, false));
    }

    @Test
    public void areEquals_whenBuloTypeDifferentFields_returnsFalse() {
        BuloIoType buloType1 = mock(BuloIoType.class);
        BuloIoType buloType2 = mock(BuloIoType.class);
        Map<String, IoType> fieldsType1 = Map.of("key1", mock(IoType.class));
        Map<String, IoType> fieldsType2 = Map.of("key2", mock(IoType.class));
        when(buloType1.getDataTypeCategory()).thenReturn(DataTypeCategory.BULO);
        when(buloType1.getFields()).thenReturn(fieldsType1);
        when(buloType2.getDataTypeCategory()).thenReturn(DataTypeCategory.BULO);
        when(buloType2.getFields()).thenReturn(fieldsType2);

        assertFalse(comparator.areEquals(buloType1, buloType2, true));
    }

    @Test
    public void areEquals_whenArrayTypeDifferentContentTypes_returnsFalse() {
        ArrayIoType arrayType1 = mock(ArrayIoType.class);
        ArrayIoType arrayType2 = mock(ArrayIoType.class);
        IoType contentType1 = mock(IoType.class);
        when(arrayType1.getDataTypeCategory()).thenReturn(DataTypeCategory.ARRAY);
        when(arrayType1.getContentType()).thenReturn(contentType1);
        when(arrayType2.getDataTypeCategory()).thenReturn(DataTypeCategory.ARRAY);
        when(arrayType2.getContentType()).thenReturn(null);

        assertFalse(comparator.areEquals(arrayType1, arrayType2, true));
    }

    @Test
    public void areEquals_whenArrayTypeSameContentTypes_butBothTypeHaveDifferentRequiredType_returnsFalse() {
        ArrayIoType arrayType1 = mock(ArrayIoType.class);
        ArrayIoType arrayType2 = mock(ArrayIoType.class);
        IoType contentType1 = mock(IoType.class);
        when(arrayType1.getDataTypeCategory()).thenReturn(DataTypeCategory.ARRAY);
        when(arrayType1.getContentType()).thenReturn(contentType1);
        when(arrayType2.getDataTypeCategory()).thenReturn(DataTypeCategory.ARRAY);
        when(arrayType2.getContentType()).thenReturn(contentType1);
        when(arrayType1.isRequired()).thenReturn(true);
        when(arrayType2.isRequired()).thenReturn(false);

        assertFalse(comparator.areEquals(arrayType1, arrayType2, false));
    }

    @Test
    public void areEquals_whenBuloTypeSameContentTypes_butBothTypeHaveDifferentRequiredType_returnsFalse() {
        BuloIoType buloIoType1 = mock(BuloIoType.class);
        BuloIoType buloIoType2 = mock(BuloIoType.class);
        when(buloIoType1.getDataTypeCategory()).thenReturn(DataTypeCategory.BULO);
        when(buloIoType2.getDataTypeCategory()).thenReturn(DataTypeCategory.BULO);
        when(buloIoType1.isRequired()).thenReturn(true);
        when(buloIoType2.isRequired()).thenReturn(false);

        assertFalse(comparator.areEquals(buloIoType1, buloIoType2, false));
    }

    @Test
    public void areEquals_whenBuloTypeSameContentTypes_bothHaveSameFieldNamesButValuesDosNotMatch_returnsFalse() {
        BuloIoType buloIoType1 = mock(BuloIoType.class);
        BuloIoType buloIoType2 = mock(BuloIoType.class);
        when(buloIoType1.getDataTypeCategory()).thenReturn(DataTypeCategory.BULO);
        when(buloIoType2.getDataTypeCategory()).thenReturn(DataTypeCategory.BULO);
        when(buloIoType1.isRequired()).thenReturn(true);
        when(buloIoType2.isRequired()).thenReturn(true);

        IoType fieldType1 = mock(IoType.class);
        IoType fieldType2 = mock(IoType.class);
        when(fieldType1.getDataTypeCategory()).thenReturn(DataTypeCategory.NATIVE);
        when(fieldType2.getDataTypeCategory()).thenReturn(DataTypeCategory.ARRAY);
        when(buloIoType1.getFields()).thenReturn(Map.of("Babar", fieldType1));
        when(buloIoType2.getFields()).thenReturn(Map.of("Babar", fieldType2));

        assertFalse(comparator.areEquals(buloIoType1, buloIoType2, false));
    }

}
