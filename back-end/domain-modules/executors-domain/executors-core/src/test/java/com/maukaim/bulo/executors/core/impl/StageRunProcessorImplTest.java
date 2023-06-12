package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.StageRunManager;
import com.maukaim.bulo.executors.data.StageDefinitionStore;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.data.result.StageRunStatus;
import com.maukaim.bulo.executors.data.runs.StageRunAncestor;
import com.maukaim.bulo.executors.data.runs.StageRunDependency;
import com.maukaim.bulo.executors.data.stages.Parameter;
import com.maukaim.bulo.executors.data.stages.Stage;
import com.maukaim.bulo.runners.api.models.StageDefinition;
import com.maukaim.bulo.runners.api.models.StageInputDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StageRunProcessorImplTest {
    private StageRunManager runnerManager;
    private StageStore stageStore;
    private StageRunResultStore stageRunResultStore;
    private StageDefinitionStore definitionStore;
    private StageRunProcessorImpl stageRunProcessor;

    private StageRunDependency dependency = mock(StageRunDependency.class);
    private final String stageRunId = "stageRunId";
    private final String stageId = "stageId";
    private final String inputId = "inputId";
    private final String definitionId = "definitionId";
    private final String ancestorStageRunId = "ancestorStageRunId";
    private final String ancestorOutputId = inputId;
    private final String ancestorOutputValue = "jsonAncestorOutputValue";
    private final String parameterName = "parameterName";
    private final String parameterValue = "parameterValue";

    private final Stage stage = mock(Stage.class);
    private StageRunAncestor ancestor = mock(StageRunAncestor.class);
    private StageRunResult ancestorStageRunResult = mock(StageRunResult.class);
    private Parameter parameter = mock(Parameter.class);

    @BeforeEach
    void init() {
        stageStore = mock(StageStore.class);
        runnerManager = mock(StageRunManager.class);
        stageRunResultStore = mock(StageRunResultStore.class);
        definitionStore = mock(StageDefinitionStore.class);

        this.stageRunProcessor = new StageRunProcessorImpl(runnerManager, stageStore, stageRunResultStore, definitionStore);
    }

    @Test
    void onCancelRequest_whenCancelIsSuccess_thenStoreCancellation() {
        when(this.runnerManager.cancel(ancestorStageRunId)).thenReturn(true);

        stageRunProcessor.onCancelRequest(ancestorStageRunId);
        verify(stageRunResultStore).put(any());
    }

    @Test
    void onCancelRequest_whenCancelHasFailed_dontStoreAnything() {
        when(this.runnerManager.cancel(ancestorStageRunId)).thenReturn(false);

        stageRunProcessor.onCancelRequest(ancestorStageRunId);
        verify(stageRunResultStore, never()).put(any());
        this.stageRunResultStore.put(StageRunResult.of(ancestorStageRunId, StageRunStatus.ACKNOWLEDGED));
    }

    @Test
    void onRunRequest_whenStageRunIsValidAndAccepted() {
        onRunRequestSuccessSetup();
        stageRunProcessor.onRunRequest(stageId, stageRunId, Set.of(dependency));

        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.ACKNOWLEDGED));
        verify(runnerManager).submit(argThat(runConfig -> runConfig.getStageRunId().equals(stageRunId)
                && runConfig.getDefinitionId().equals(definitionId)
        ));
        verify(stageRunResultStore, never()).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.CANCELLED));
    }

    @Test
    void onRunRequest_whenStageIsNotInStore_thenResultIsCancel() {
        onRunRequestSuccessSetup();
        when(stageStore.getById(stageId)).thenReturn(null);

        stageRunProcessor.onRunRequest(stageId, stageRunId, Set.of(dependency));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.ACKNOWLEDGED));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.CANCELLED));
    }

    @Test
    void onRunRequest_whenAncestorResultIsNull_thenResultIsCancel() {
        onRunRequestSuccessSetup();
        when(stageRunResultStore.getById(ancestorStageRunId)).thenReturn(null);

        stageRunProcessor.onRunRequest(stageId, stageRunId, Set.of(dependency));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.ACKNOWLEDGED));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.CANCELLED));
    }

    @Test
    void onRunRequest_whenAncestorResultIsDifferentThanSuccess_thenResultIsCancel() {
        onRunRequestSuccessSetup();
        when(ancestorStageRunResult.getStatus()).thenReturn(StageRunStatus.RUNNING);

        stageRunProcessor.onRunRequest(stageId, stageRunId, Set.of(dependency));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.ACKNOWLEDGED));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.CANCELLED));
    }

    @Test
    void onRunRequest_whenStageParametersAreNull_thenResultIsCancel() {
        onRunRequestSuccessSetup();
        when(stage.getParameters()).thenReturn(null);

        stageRunProcessor.onRunRequest(stageId, stageRunId, Set.of(dependency));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.ACKNOWLEDGED));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.CANCELLED));
    }

    @Test
    void onRunRequest_whenNoInputFromAncestorButRequired_thenResultIsCancel() {
        onRunRequestSuccessSetup();
        setupWhenNoInputFromAncestor(true);

        stageRunProcessor.onRunRequest(stageId, stageRunId, Set.of(dependency));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.ACKNOWLEDGED));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.CANCELLED));
    }

    @Test
    void onRunRequest_whenNoInputFromAncestorButNotRequired_thenSuccess() {
        onRunRequestSuccessSetup();
        setupWhenNoInputFromAncestor(false);

        stageRunProcessor.onRunRequest(stageId, stageRunId, Set.of(dependency));

        verify(runnerManager).submit(any());
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.ACKNOWLEDGED));
        verify(stageRunResultStore, never()).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.CANCELLED));
    }

    @Test
    void onRunRequest_whenRunnerManagerDontAcceptTheStageRun_thenResultIsCancel() {
        onRunRequestSuccessSetup();
        when(runnerManager.submit(any())).thenReturn(false);

        stageRunProcessor.onRunRequest(stageId, stageRunId, Set.of(dependency));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.ACKNOWLEDGED));
        verify(stageRunResultStore).put(argThat(stageRunResult -> stageRunResult.getStatus() == StageRunStatus.CANCELLED));
    }

    private void onRunRequestSuccessSetup() {
        when(stageStore.getById(stageId)).thenReturn(stage);
        when(dependency.getInputId()).thenReturn(inputId);

        when(dependency.getAncestors()).thenReturn(Set.of(ancestor));
        when(ancestor.getStageRunId()).thenReturn(ancestorStageRunId);
        when(stageRunResultStore.getById(ancestorStageRunId)).thenReturn(ancestorStageRunResult);
        when(ancestorStageRunResult.getStatus()).thenReturn(StageRunStatus.SUCCESS);
        when(ancestor.getOutputIds()).thenReturn(Set.of(ancestorOutputId));
        when(ancestorStageRunResult.getOutputs()).thenReturn(Map.of(ancestorOutputId, ancestorOutputValue));

        when(stage.getParameters()).thenReturn(List.of(parameter));
        when(parameter.getName()).thenReturn(parameterName);
        when(parameter.getValue()).thenReturn(parameterValue);
        when(stage.getDefinitionId()).thenReturn(definitionId);

        when(runnerManager.submit(any())).thenReturn(true);
    }

    private void setupWhenNoInputFromAncestor(boolean isInputRequired) {
        StageDefinition definition = mock(StageDefinition.class);
        when(ancestor.getOutputIds()).thenReturn(Set.of("otherId"));
        when(definitionStore.getById(definitionId)).thenReturn(definition);
        StageInputDefinition inputDefinition = mock(StageInputDefinition.class);
        when(definition.getInputsByName()).thenReturn(Map.of(inputId, inputDefinition));
        when(inputDefinition.isRequired()).thenReturn(isInputRequired);
    }

}