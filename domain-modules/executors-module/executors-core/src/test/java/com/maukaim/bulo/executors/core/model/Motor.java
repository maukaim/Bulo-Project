package com.maukaim.bulo.executors.core.model;

import com.maukaim.bulo.api.data.types.annotations.BuloDescriptor;
import com.maukaim.bulo.api.data.types.annotations.BuloField;

public class Motor {
    private Integer vitesseMax;

    @BuloDescriptor
    public Motor(@BuloField("vitesseMax") Integer vitesseMax) {
        this.vitesseMax = vitesseMax;
    }


    public Integer getVitesseMax() {
        return vitesseMax;
    }

    public void setVitesseMax(Integer vitesseMax) {
        this.vitesseMax = vitesseMax;
    }
}
