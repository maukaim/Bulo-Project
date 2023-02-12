package com.maukaim.bulo.commons.io.data.types;

import com.maukaim.bulo.api.data.types.DataTypeCategory;

/**
 * Just a Marker to make Io & Parameter impls diverge if required.
 */
public interface ParameterTypeDto {
    boolean isRequired();
    DataTypeCategory getDataTypeCategory();
}
