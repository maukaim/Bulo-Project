package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.RunOperator;
import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.core.marshalling.MarshallerProvider;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.runners.api.RunnerMarshaller;
import com.maukaim.bulo.runners.api.StageRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BuloRunOperatorProviderTest {
    private BuloRunOperatorProvider provider;
    private StageRunResultStore store;
    private MarshallerProvider marshallerProvider;

    @BeforeEach
    void setUp() {
        store = mock(StageRunResultStore.class);
        marshallerProvider = mock(MarshallerProvider.class);
        provider = new BuloRunOperatorProvider(store, marshallerProvider);
    }

    @Test
    void get_whenCalledWithValidParameters() {
        StageRunner runner = mock(StageRunner.class);
        StageRunConfig config = mock(StageRunConfig.class);
        when(config.getInputsByName()).thenReturn(mock(Map.class));
        when(config.getParametersByName()).thenReturn(mock(Map.class));
        when(marshallerProvider.get()).thenReturn(mock(RunnerMarshaller.class));

        RunOperator result = provider.get(runner, config);

        assertTrue(result instanceof RunOperatorImpl);
    }

    @Test
    void get_whenCalledWithNullRunner_throwsException() {
        StageRunConfig config = mock(StageRunConfig.class);

        assertThrows(IllegalArgumentException.class, () -> provider.get(null, config));
    }

    @Test
    void get_whenCalledWithNullConfig_throwsException() {
        StageRunner runner = mock(StageRunner.class);

        assertThrows(IllegalArgumentException.class, () -> provider.get(runner, null));
    }

    @Test
    void get_whenCalledWithNullInputs_throwsException() {
        StageRunner runner = mock(StageRunner.class);
        StageRunConfig config = mock(StageRunConfig.class);
        when(config.getInputsByName()).thenReturn(null);
        when(config.getParametersByName()).thenReturn(mock(Map.class));
        when(marshallerProvider.get()).thenReturn(mock(RunnerMarshaller.class));

        assertThrows(IllegalArgumentException.class, () -> provider.get(runner, config));
    }

    @Test
    void get_whenCalledWithNullParameters_throwsException() {
        StageRunner runner = mock(StageRunner.class);
        StageRunConfig config = mock(StageRunConfig.class);
        when(config.getInputsByName()).thenReturn(mock(Map.class));
        when(config.getParametersByName()).thenReturn(null);
        when(marshallerProvider.get()).thenReturn(mock(RunnerMarshaller.class));

        assertThrows(IllegalArgumentException.class, () -> provider.get(runner, config));
    }

    @Test
    void get_whenMarshallerProviderProvidesNullValue_throwsException() {
        StageRunner runner = mock(StageRunner.class);
        StageRunConfig config = mock(StageRunConfig.class);
        when(config.getInputsByName()).thenReturn(mock(Map.class));
        when(config.getParametersByName()).thenReturn(mock(Map.class));
        when(marshallerProvider.get()).thenReturn(null);

        assertThrows(IllegalStateException.class, () -> provider.get(runner, config));
    }
}