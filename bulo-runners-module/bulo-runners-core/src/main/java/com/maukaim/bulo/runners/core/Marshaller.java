package com.maukaim.bulo.runners.core;

import java.util.Collection;

public interface Marshaller {
    String marshall(Object obj);
    <T> T unmarshall(String marshall, Class<T> clazz);
    <T> Collection<T> unmarshallAsCollection(String marshall, Class<T> clazz);
}
