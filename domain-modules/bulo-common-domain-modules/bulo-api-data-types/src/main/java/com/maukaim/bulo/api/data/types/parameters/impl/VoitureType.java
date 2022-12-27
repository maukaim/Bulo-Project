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

/**
 * Required notRequired servent a prendre le XXType comme il est, avec ses "required/notRequired"
 * Le constructor sert a override les informations, pour si jamais on a besoin d'utiliser le type de maniere particuliere
 *  i.e, si MotorType par defaut dit que sa vitesseMax est optional, on peut dire que dans une VoitureType, il faut que ce soit MANDATORY
 */