package com.maukaim.bulo.api.data.types.any;

import com.maukaim.bulo.api.data.types.Any;

import java.util.Map;

public class AnyObject extends Any<Map<String, ? extends Any<?>>> {

    public AnyObject(Map<String, ? extends Any<?>> value) {
        super(value);
    }

}
