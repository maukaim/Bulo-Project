package com.maukaim.bulo.executors.core.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RunExecutorImplTest {
    private ExecutorService executorService = mock(ExecutorService.class);
    private RunExecutorImpl executor;

    @BeforeEach
    void init() {
        this.executor = new RunExecutorImpl(executorService);
    }

    @Test
    void async_whenSubmitCallable_ReturnsAFuture() {
        Runnable runnable = mock(Runnable.class);
        Future future = mock(Future.class);
        when(executorService.submit(runnable)).thenReturn(future);

        Future<?> result = executor.async(runnable);
        assertSame(future, result);
    }
}
