package com.maukaim.bulo.api.data.types.parameters.impl;


import com.maukaim.bulo.api.data.types.natives.NumberType;
import com.maukaim.bulo.api.data.types.parameters.BuloParameterType;
import com.maukaim.bulo.api.data.types.parameters.ParameterType;

import java.util.Map;

public class MotorType extends BuloParameterType {

    public static BuloParameterType required() {
        return get(true);
    }

    public static BuloParameterType notRequired() {
        return get(false);
    }

    private static BuloParameterType get(boolean isRequired) {
        return BuloParameterType.of(Map.of(
                "vitesseMax", NumberType.required()
        ), isRequired);
    }

    public MotorType(Map<String, ParameterType> fields, boolean isRequired) {
        super(fields, isRequired);
    }
}
