package com.maukaim.bulo.executors.core.marshalling.jackson.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.function.Supplier;

public interface ObjectMapperProvider extends Supplier<ObjectMapper> {

}
