package com.maukaim.bulo.ms.shared.system.endpoints;

import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ForServiceEventType {
    MicroServiceEventType value();
}
