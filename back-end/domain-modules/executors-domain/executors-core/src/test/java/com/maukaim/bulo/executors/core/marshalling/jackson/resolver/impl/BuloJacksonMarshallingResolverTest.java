package com.maukaim.bulo.executors.core.marshalling.jackson.resolver.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.executors.core.marshalling.jackson.generator.MixInGeneratorStrategy;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class BuloJacksonMarshallingResolverTest {
    private final MixInGeneratorStrategy strategy = mock(MixInGeneratorStrategy.class);
    private final ObjectMapper objectMapper = mock(ObjectMapper.class);
    private final BuloJacksonMarshallingResolver resolver = new BuloJacksonMarshallingResolver(strategy);

    @Test
    public void resolveDeserialization_whenMixInsNotNull_applyMixInsToMapper() {
        Map<Class<?>, Class<?>> mockMixIns = mock(Map.class);
        when(strategy.chainedGetMixIn(anyString(), any(Class.class))).thenReturn(mockMixIns);

        resolver.resolveDeserialization("test", String.class, objectMapper);

        verify(mockMixIns, times(1)).forEach(any());
    }

    @Test
    public void resolveDeserialization_whenMixInsNull_noOperationOnMapper() {
        when(strategy.chainedGetMixIn(anyString(), any(Class.class))).thenReturn(null);

        resolver.resolveDeserialization("test", String.class, objectMapper);

        verifyNoInteractions(objectMapper);
    }


    @Test
    public void resolveSerialization_whenCalled_doesNothing() {
        Object object = new Object();

        resolver.resolveSerialization(object, objectMapper);

        verifyNoInteractions(objectMapper);
    }
}