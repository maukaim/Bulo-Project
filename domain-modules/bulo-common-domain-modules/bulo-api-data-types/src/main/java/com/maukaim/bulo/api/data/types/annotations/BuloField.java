package com.maukaim.bulo.api.data.types.annotations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@JsonProperty
public @interface BuloField {
    String value();
    boolean isRequired() default true;
    BuloFieldOverride[] fields() default {};
}
