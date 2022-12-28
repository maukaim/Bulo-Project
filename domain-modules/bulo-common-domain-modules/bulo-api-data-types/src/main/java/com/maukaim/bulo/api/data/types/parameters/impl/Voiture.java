package com.maukaim.bulo.api.data.types.parameters.impl;

import com.maukaim.bulo.api.data.types.parameters.BuloField;
import com.maukaim.bulo.api.data.types.parameters.BuloFieldOverride;
import com.maukaim.bulo.api.data.types.parameters.BuloParameter;

public class Voiture {
    private Motor motor;
    private String brand;
    private Boolean isUsable;

    @BuloParameter
    public Voiture(
            @BuloField(isRequired = false, fields = {
                    @BuloFieldOverride(name = "vitesseMax", isRequired = false)
            })
            Motor motor,
            Motor motor2,
            @BuloField(isRequired = false) String brand,
            Boolean isUsable) {
        this.motor = motor;
        this.brand = brand;
        this.isUsable = isUsable;
    }

    public class Motor {
        private Integer vitesseMax;

        @BuloParameter
        public Motor( Integer vitesseMax) {
            this.vitesseMax = vitesseMax;
        }
    }
}
