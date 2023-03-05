package com.maukaim.bulo.api.data.types.parameters.impl;


import com.maukaim.bulo.api.data.types.natives.BooleanType;
import com.maukaim.bulo.api.data.types.natives.StringType;
import com.maukaim.bulo.api.data.types.parameters.BuloParameterType;
import com.maukaim.bulo.api.data.types.parameters.ParameterType;

import java.util.Map;


public class VoitureType extends BuloParameterType {


    public static BuloParameterType required() {
        return get(true);
    }

    public static BuloParameterType notRequired() {
        return get(false);
    }

    private static BuloParameterType get(boolean isRequired) {
        return BuloParameterType.of(Map.of(
                "motor", MotorType.required(),
                "brand", StringType.required(),
                "isUsable", BooleanType.required()
        ), isRequired);
    }

    protected VoitureType(Map<String, ParameterType> fields, boolean isRequired) {
        super(fields, isRequired);
    }
}