package com.maukaim.bulo.executors.core.marshalling.jackson.generator;


import java.util.Map;

public abstract class MixInGeneratorStrategy {

    public Map<Class<?>,Class<?>> chainedGetMixIn(String value, Class<?> clazz) {
        Map<Class<?>,Class<?>> mixInsMap = generateMixIns(value, clazz);
        if (mixInsMap == null || mixInsMap.isEmpty()) {
            MixInGeneratorStrategy nextCreator = andThen();
            if (andThen() != null) {
                return nextCreator.chainedGetMixIn(value, clazz);
            }
            return Map.of();
        }
        return mixInsMap;
    }

    protected abstract Map<Class<?>,Class<?>> generateMixIns(String value, Class<?> clazz);

    protected MixInGeneratorStrategy andThen() {
        return null;
    }

}
