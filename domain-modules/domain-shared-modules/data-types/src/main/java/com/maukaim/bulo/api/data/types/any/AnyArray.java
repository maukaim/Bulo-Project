package com.maukaim.bulo.api.data.types.any;

import com.maukaim.bulo.api.data.types.Any;

import java.util.List;

public class AnyArray extends Any<List<? extends Any<?>>> {
    public AnyArray(List<? extends Any<?>> value) {
        super(value);
    }

}
