package com.maukaim.bulo.executors.core.model;

import com.maukaim.bulo.api.data.types.annotations.BuloDescriptor;
import com.maukaim.bulo.api.data.types.annotations.BuloField;
import com.maukaim.bulo.api.data.types.annotations.BuloFieldOverride;

/**
 * Requirements:
 * Should have at least 1 @BuloDescriptor
 */
public class Voiture {
    private final Motor motor;
    private final String brand;
    private final Boolean isUsable;

    @BuloDescriptor
    public Voiture(
            @BuloField(value = "motor",
                    isRequired = false,
                    fields = {@BuloFieldOverride(name = "vitesseMax", isRequired = false)
                    })
            Motor motor,
            @BuloField(value = "brand", isRequired = false)
            String brand,
            @BuloField("usable")
            Boolean usable) {
        this.motor = motor;
        this.brand = brand;
        this.isUsable = usable;
    }

    public Motor getMotor() {
        return motor;
    }

    public String getBrand() {
        return brand;
    }

    public Boolean getUsable() {
        return isUsable;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "motor=" + motor +
                ", brand='" + brand + '\'' +
                ", isUsable=" + isUsable +
                '}';
    }
}
