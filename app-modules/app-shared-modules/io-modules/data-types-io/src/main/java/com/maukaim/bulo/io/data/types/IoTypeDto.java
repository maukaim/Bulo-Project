package com.maukaim.bulo.io.data.types;

import com.maukaim.bulo.api.data.types.DataTypeCategory;

/**
 * Just a Marker to make Io & Parameter impls diverge if required.
 */
public interface IoTypeDto {
    boolean isRequired();
    DataTypeCategory getDataTypeCategory();
}
