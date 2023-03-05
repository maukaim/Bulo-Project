package com.maukaim.bulo.io.data.types;

/**
 * Just a Marker to make Io & Parameter impls diverge if required.
 */
public interface ParameterTypeDto {
    boolean isRequired();

    DataTypeCategoryDto getDataTypeCategory();
}
