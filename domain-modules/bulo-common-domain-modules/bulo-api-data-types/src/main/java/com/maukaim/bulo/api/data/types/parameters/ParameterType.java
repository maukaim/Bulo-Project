package com.maukaim.bulo.api.data.types.parameters;

import com.maukaim.bulo.api.data.types.DataType;

/**
 * Just a Marker to make Io & Parameter impls diverge if required.
 */
public interface ParameterType extends DataType {
    default boolean isRequired() {
        return true;
    }

    default ArrayParameterType asParamList() {
        return new ArrayParameterType(this, isRequired());
    }
}
