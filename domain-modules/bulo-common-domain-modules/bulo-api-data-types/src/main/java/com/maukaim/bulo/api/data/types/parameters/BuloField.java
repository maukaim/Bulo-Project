package com.maukaim.bulo.api.data.types.parameters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface BuloField {
    boolean isRequired() default true;
    BuloFieldOverride[] fields() default {};
}
