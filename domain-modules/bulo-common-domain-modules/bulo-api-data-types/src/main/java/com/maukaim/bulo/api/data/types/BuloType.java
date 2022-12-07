package com.maukaim.bulo.api.data.types;

import java.util.Map;

public interface BuloType<T extends DataType> extends DataType {
    Map<String, T> getFields();
    default DataTypeCategory getDataTypeCategory() {
        return DataTypeCategory.BULO;
    }
}
