package com.maukaim.bulo.api.data.types.io;

import com.maukaim.bulo.api.data.types.DataType;

/**
 * Just a Marker to make Io & Parameter impls diverge if required.
 */
public interface IoType extends DataType {
    default boolean isRequired(){
        return true;
    }
}
