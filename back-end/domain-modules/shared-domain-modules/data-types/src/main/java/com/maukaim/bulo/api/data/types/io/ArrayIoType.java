package com.maukaim.bulo.api.data.types.io;

import com.maukaim.bulo.api.data.types.ArrayType;

public class ArrayIoType extends ArrayType<IoType> implements IoType {
    public ArrayIoType(IoType contentType, boolean required) {
        super(contentType, required);
    }
}
