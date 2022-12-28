package com.maukaim.bulo.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.api.data.types.DataType;
import com.maukaim.bulo.api.data.types.io.ArrayIoType;
import com.maukaim.bulo.api.data.types.io.BuloIoType;
import com.maukaim.bulo.api.data.types.io.IoType;

import java.util.Map;

import static com.maukaim.bulo.api.data.types.DataTypeCategory.ARRAY;

public class IoTypeComparator {

    public static boolean areEquals(IoType type1, IoType type2, boolean skipIsRequiredCheck) {
        if (sameInMemory(type1, type2)) {
            return true;
        }

        if (onlyOneIsNull(type1, type2)) {
            return false;
        }

        if (dataTypeCategoriesAreDifferent(type1, type2)) {
            return false;
        }
        return switch (type1.getDataTypeCategory()) {
            case NATIVE ->
                    type1.getClass().equals(type2.getClass()) && isRequiredIsValid(skipIsRequiredCheck, type1, type2);
            case BULO -> areEquals((BuloIoType) type1, (BuloIoType) type2, skipIsRequiredCheck);
            case ARRAY -> ARRAY.equals(type2.getDataTypeCategory()) &&
                    areEquals((ArrayIoType) type1, (ArrayIoType) type2, skipIsRequiredCheck);
        };
    }

    private static boolean areEquals(ArrayIoType type1, ArrayIoType type2, boolean skipIsRequiredCheck) {
        return areEquals(type1.getContentType(), type2.getContentType(), false) &&
                isRequiredIsValid(skipIsRequiredCheck, type1, type2);
    }

    private static boolean isRequiredIsValid(boolean skipIsRequiredCheck, IoType type1, IoType type2) {
        return skipIsRequiredCheck || type1.isRequired() == type2.isRequired();
    }

    private static boolean areEquals(BuloIoType type1, BuloIoType type2, boolean skipIsRequiredCheck) {
        if (!skipIsRequiredCheck && type1.isRequired() != type2.isRequired()) {
            return false;
        }
        return areEquals(type1.getFields(), type2.getFields());
    }

    private static boolean areEquals(Map<String, IoType> fieldsType1, Map<String, IoType> fieldsType2) {
        if (!fieldsType1.keySet().equals(fieldsType2.keySet())) {
            return false;
        } else {
            return fieldsType1.entrySet().stream().allMatch(entry -> {
                        IoType fields1 = entry.getValue();
                        IoType fields2 = fieldsType2.get(entry.getKey());
                        return areEquals(fields1, fields2, false);
                    }
            );
        }
    }

    private static boolean dataTypeCategoriesAreDifferent(IoType type1, IoType type2) {
        return type1.getDataTypeCategory() != type2.getDataTypeCategory();
    }

    private static boolean onlyOneIsNull(DataType type1, IoType type2) {
        return (type1 == null && type2 != null) ||
                (type1 != null && type2 == null);
    }

    private static boolean sameInMemory(IoType type1, IoType type2) {
        return type1 == type2;
    }
}
