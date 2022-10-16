package com.maukaim.bulo.runners.core;

import com.maukaim.bulo.executors.data.StageRunner;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractStageRunner implements StageRunner {
    protected final Marshaller marshaller;

    public AbstractStageRunner(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    protected String toOutput(Object obj) {
        return this.marshaller.marshall(obj);
    }

    private <T> T castObject(String marshall, Class<T> clazz) {
        if (String.class.isAssignableFrom(clazz)) {
            return (T) marshall;
        }
        return this.marshaller.unmarshall(marshall, clazz);
    }

    private <T> Collection<T> castCollection(String marshall, Class<T> clazz) {
        return this.marshaller.unmarshallAsCollection(marshall, clazz);
    }

    protected <T> T getOrThrow(Map<String, String> valueMap, String key, Class<T> clazz) {
        if (valueMap.containsKey(key)) {
            String value = valueMap.get(key);
            return castObject(value, clazz);
        } else {
            throw new MissingInputException("Following value is missing: " + key);
        }
    }

    protected <T> Collection<T> getCollectionOrThrow(Map<String, String> valueMap, String key, Class<T> clazz) {
        if (valueMap.containsKey(key)) {
            String value = valueMap.get(key);
            return castCollection(value, clazz);
        } else {
            throw new MissingInputException("Following value is missing: " + key);
        }
    }
}
