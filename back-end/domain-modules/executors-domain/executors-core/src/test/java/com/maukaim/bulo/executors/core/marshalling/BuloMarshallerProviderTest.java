package com.maukaim.bulo.executors.core.marshalling;

import com.maukaim.bulo.executors.core.marshalling.jackson.mapper.ObjectMapperProvider;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonDeserializationResolver;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonSerializationResolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BuloMarshallerProviderTest {
    private final ObjectMapperProvider objectMapperProvider = mock(ObjectMapperProvider.class);
    private final JacksonSerializationResolver serializationResolver = mock(JacksonSerializationResolver.class);
    private final JacksonDeserializationResolver deserializationResolver = mock(JacksonDeserializationResolver.class);
    private final BuloMarshallerProvider buloMarshallerProvider = new BuloMarshallerProvider(objectMapperProvider, serializationResolver, deserializationResolver);

    @Test
    void get_notNull() {
        assertNotNull(buloMarshallerProvider.get());
        verify(objectMapperProvider).get();
    }
}