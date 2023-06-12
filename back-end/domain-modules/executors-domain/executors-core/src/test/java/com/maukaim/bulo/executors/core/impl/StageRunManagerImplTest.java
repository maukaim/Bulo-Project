package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.RunExecutor;
import com.maukaim.bulo.executors.core.RunOperator;
import com.maukaim.bulo.executors.core.RunOperatorProvider;
import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.core.StageRunnerRegistry;
import com.maukaim.bulo.runners.api.StageRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StageRunManagerImplTest {
    private StageRunnerRegistry runnerRegistry;
    private RunExecutor runExecutor;
    private RunOperatorProvider runOperatorProvider;
    private StageRunManagerImpl stageRunManager;

    private final StageRunConfig stageRunConfig =mock(StageRunConfig.class);
    private final String definitionId = "definitionId";
    private final String stageRunId = "stageRunId";
    private final Future future = mock(Future.class);
    @BeforeEach
    void setUp() {
        runnerRegistry = mock(StageRunnerRegistry.class);
        runExecutor = mock(RunExecutor.class);
        runOperatorProvider = mock(RunOperatorProvider.class);
        stageRunManager = new StageRunManagerImpl(runnerRegistry, runExecutor, runOperatorProvider);

        submitSuccessSetup();
    }

    @Test
    void submit_returnTrue(){
        boolean wasSubmitted = stageRunManager.submit(stageRunConfig);

        assertTrue(wasSubmitted);
        verify(runOperatorProvider).get(any(), any());
        verify(runExecutor).async(any());
    }

    @Test
    void submit_whenRunnerRegistryDoesNotKnowTheDefinitionId_returnFalse(){
        when(runnerRegistry.getByDefinitionId(definitionId)).thenReturn(null);

        boolean wasSubmitted = stageRunManager.submit(stageRunConfig);

        assertFalse(wasSubmitted);
    }

    @Test
    void submit_whenRunExecutorThrows_returnFalse(){
        when(runExecutor.async(any())).thenThrow(new RuntimeException());

        boolean wasSubmitted = stageRunManager.submit(stageRunConfig);

        assertFalse(wasSubmitted);
        verify(runOperatorProvider).get(any(), any());
        verify(runExecutor).async(any());
    }


    @Test
    void cancel_successFully(){
        stageRunManager.submit(stageRunConfig);
        when(future.cancel(true)).thenReturn(true);

        boolean isCancelled = stageRunManager.cancel(stageRunId);

        assertTrue(isCancelled);
        verify(future).cancel(true);
    }

    @Test
    void cancel_whenNotCancellableAnymore(){
        stageRunManager.submit(stageRunConfig);
        when(future.cancel(true)).thenReturn(false);

        boolean isCancelled = stageRunManager.cancel(stageRunId);

        assertFalse(isCancelled);
        verify(future).cancel(true);
    }

    @Test
    void cancel_whenNothingToCancel(){
        boolean wasSubmitted = stageRunManager.submit(stageRunConfig);

        boolean isCancelled = stageRunManager.cancel(stageRunId);

        assertFalse(isCancelled);
        verify(future).cancel(true);
    }

    private void submitSuccessSetup() {
        StageRunner stageRunner = mock(StageRunner.class);
        RunOperator runOperator = mock(RunOperator.class);
        when(stageRunConfig.getDefinitionId()).thenReturn(definitionId);
        when(runnerRegistry.getByDefinitionId(definitionId)).thenReturn(stageRunner);
        when(runOperatorProvider.get(any(), any())).thenReturn(runOperator);
        when(runExecutor.async(runOperator)).thenReturn(future);
        when(stageRunConfig.getStageRunId()).thenReturn(stageRunId);
    }
}