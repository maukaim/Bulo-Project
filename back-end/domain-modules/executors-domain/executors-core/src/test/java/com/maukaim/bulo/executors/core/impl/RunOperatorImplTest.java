package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.data.result.StageRunStatus;
import com.maukaim.bulo.runners.api.ExecutionCancelledException;
import com.maukaim.bulo.runners.api.StageRunner;
import com.maukaim.bulo.runners.api.StageRunnerContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RunOperatorImplTest {
    private final ArgumentCaptor<StageRunResult> argCaptor = ArgumentCaptor.forClass(StageRunResult.class);
    private final StageRunner runner = mock(StageRunner.class);
    private final StageRunConfig config = mock(StageRunConfig.class);
    private final StageRunResultStore resultStore = mock(StageRunResultStore.class);
    private final StageRunnerContext ctx = mock(StageRunnerContext.class);

    private final String stageRunId = "1234";

    @BeforeEach
    void init(){
        when(config.getStageRunId()).thenReturn(stageRunId);
    }

    @Test
    void run_whenExecutionSucceeds_runStatusIsSuccess() {
        Map<String, String> runnerResult = new HashMap<>();
        runnerResult.put("key", "value");
        when(runner.run(ctx)).thenReturn(runnerResult);

        RunOperatorImpl operator = new RunOperatorImpl(runner, config, resultStore, ctx);

        // Act
        operator.run();

        // Assert
        verify(resultStore, times(2)).put(argCaptor.capture());
        List<StageRunResult> allValues = argCaptor.getAllValues();
        assertEquals(2, allValues.size());
        assertEquals(StageRunStatus.SUCCESS, allValues.get(1).getStatus());
    }

    @Test
    void run_whenExecutionCancelled_runStatusIsCancelled() {
        when(runner.run(ctx)).thenThrow(new ExecutionCancelledException());

        RunOperatorImpl operator = new RunOperatorImpl(runner, config, resultStore, ctx);

        // Act
        operator.run();

        // Assert
        verify(resultStore, times(2)).put(argCaptor.capture());
        List<StageRunResult> allValues = argCaptor.getAllValues();
        assertEquals(2, allValues.size());
        assertEquals(StageRunStatus.CANCELLED, allValues.get(1).getStatus());
    }

    @Test
    void run_whenExecutionThrowsException_runStatusIsFailed() {
        when(runner.run(ctx)).thenThrow(new RuntimeException("Error"));

        RunOperatorImpl operator = new RunOperatorImpl(runner, config, resultStore, ctx);

        // Act
        operator.run();

        // Assert
        verify(resultStore, times(2)).put(argCaptor.capture());
        List<StageRunResult> allValues = argCaptor.getAllValues();
        assertEquals(2, allValues.size());
        assertEquals(StageRunStatus.FAILED, allValues.get(1).getStatus());
    }
}
