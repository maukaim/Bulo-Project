package com.maukaim.bulo.api.data.types.annotations;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
@JsonCreator
public @interface BuloDescriptor{
}
