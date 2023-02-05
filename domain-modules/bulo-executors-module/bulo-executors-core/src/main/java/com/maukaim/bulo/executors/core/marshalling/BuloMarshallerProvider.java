package com.maukaim.bulo.executors.core.marshalling;

import com.maukaim.bulo.executors.core.marshalling.jackson.mapper.ObjectMapperProvider;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonDeserializationResolver;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonSerializationResolver;
import com.maukaim.bulo.runners.api.RunnerMarshaller;

public class BuloMarshallerProvider implements MarshallerProvider {
    private final ObjectMapperProvider mapperProvider;
    private final JacksonSerializationResolver serializationResolver;
    private final JacksonDeserializationResolver deserializationResolver;

    public BuloMarshallerProvider(ObjectMapperProvider mapperProvider,
                                  JacksonSerializationResolver serializationResolver,
                                  JacksonDeserializationResolver deserializationResolver) {
        this.mapperProvider = mapperProvider;
        this.serializationResolver = serializationResolver;
        this.deserializationResolver = deserializationResolver;
    }

    @Override
    public RunnerMarshaller get() {
        return new BuloRunnerMarshaller(mapperProvider.get(), serializationResolver, deserializationResolver);
    }
}
