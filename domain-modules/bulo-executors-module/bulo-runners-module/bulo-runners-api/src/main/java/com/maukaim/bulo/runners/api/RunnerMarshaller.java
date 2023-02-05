package com.maukaim.bulo.runners.api;

import com.maukaim.bulo.api.data.types.Any;
import com.maukaim.bulo.marshalling.Marshaller;

import java.util.Collection;

public interface RunnerMarshaller extends Marshaller {
    Any<?> unmarshallAsGenericType(String marshall);
    <T> Collection<T> unmarshallAsCollection(String marshall, Class<T> clazz);
}
