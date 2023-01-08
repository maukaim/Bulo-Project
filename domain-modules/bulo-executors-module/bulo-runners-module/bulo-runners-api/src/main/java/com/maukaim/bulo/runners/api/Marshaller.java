package com.maukaim.bulo.runners.api;

import com.maukaim.bulo.api.data.types.Any;

import java.util.Collection;

public interface Marshaller {
    String marshall(Object obj);
    <T> T unmarshall(String marshall, Class<T> clazz);
    Any<?> unmarshallAsGenericType(String marshall);
    <T> Collection<T> unmarshallAsCollection(String marshall, Class<T> clazz);
}
