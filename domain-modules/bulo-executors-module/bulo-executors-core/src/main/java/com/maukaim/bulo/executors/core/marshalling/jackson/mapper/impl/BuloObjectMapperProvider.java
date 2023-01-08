package com.maukaim.bulo.executors.core.marshalling.jackson.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.executors.core.marshalling.jackson.mapper.AbstractObjectMapperProvider;

public class BuloObjectMapperProvider extends AbstractObjectMapperProvider {
    @Override
    public ObjectMapper get() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(getDefaultModule());
        applyDefaultSettings(objectMapper);
        return objectMapper;
    }
}
