package com.maukaim.bulo.marshalling;

public interface Marshaller {
    String marshall(Object obj);
    <T> T unmarshall(String marshall, Class<T> clazz);
}
