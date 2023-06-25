package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.utils.OrchestrableContextStatusResolver;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;

import static com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRunStatus.TO_BE_REQUESTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class StageRunServiceImplTest {
    private final StageRunConnector stageRunConnector = mock(StageRunConnector.class);
    private final DummyStageRunStore stageRunStore = mock(DummyStageRunStore.class); //May want to do DummyTHing
    private final ExecutorService executorService = mock(ExecutorService.class);
    private final FunctionalStageService functionalStageService = mock(FunctionalStageService.class);
    private final FunctionalStageDefinitionService functionalStageDefinitionService = mock(FunctionalStageDefinitionService.class);
    private final FunctionalStageRunFactory functionalStageRunFactory = mock(FunctionalStageRunFactory.class);
    private final TechnicalStageRunFactory technicalStageRunFactory = mock(TechnicalStageRunFactory.class);
    private final OrchestrableContextStatusResolver orchestrableContextStatusResolver = mock(OrchestrableContextStatusResolver.class);

    private final FunctionalStageRun funcStageRun = mock(FunctionalStageRun.class);
    private final FunctionalStageRun newFuncStageRun = mock(FunctionalStageRun.class);
    private final FunctionalStageRun newFuncStageRunAfterStateUpdate = mock(FunctionalStageRun.class);
    private final FunctionalStageRun newFuncStageRunAfterRequest = mock(FunctionalStageRun.class);
    private final FunctionalStageRun newFuncStageRunAfterRequestAfterStateUpdate = mock(FunctionalStageRun.class);
    private final FunctionalStageRun funcStageRunToBePersisted = mock(FunctionalStageRun.class);

    private final String stageRunId = "stageRunId";
    private final String executorId = "executorId";
    private final String aFunctionalStageRunId = "aFunctionalStageRunId";
    private final TechnicalStageRun aTechStageRun = mock(TechnicalStageRun.class);
    private final String aTechStageRunId = "aTechStageRunId";
    private final String aTechStageId = "aTechStageId";
    private final ContextStageId aTechStageContextId = mock(ContextStageId.class);
    private final FunctionalStageRun aFuncStageRun = mock(FunctionalStageRun.class);
    private final FunctionalStageRun anOtherFunctionalStageRun = mock(FunctionalStageRun.class);
    private final String aFuncStageRunId = "aFuncStageRunId";
    private final ExecutionGraph executionGraph = mock(ExecutionGraph.class);
    private final ContextStageId aFuncStageContextId = mock(ContextStageId.class);
    private final RunContext<String> aRunContext = mock(RunContext.class);
    private final RunDependency aRunDependency = mock(RunDependency.class);
    private final ContextStageId aRootContextId = mock(ContextStageId.class);
    private final TechnicalStageRun anOtherTechnicalStageRun = mock(TechnicalStageRun.class);
    private final String aTechnicalStageRunId = "aTechnicalStageRunId";
    private final TechnicalStageRun aTechStageRunInflight = mock(TechnicalStageRun.class);
    private final String aTechStageRunInflightId = "aTechStageRunInflightId";
    private final FunctionalStageRun aFuncStageRunInflight = mock(FunctionalStageRun.class);
    private final String aFuncStageRunInflightId = "aFuncStageRunInflightId";

    private final StageRunService stageRunService = new StageRunServiceImpl(
            stageRunConnector,
            stageRunStore,
            executorService,
            functionalStageService,
            functionalStageDefinitionService,
            functionalStageRunFactory,
            technicalStageRunFactory,
            orchestrableContextStatusResolver
    );

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage_andNewStatusIsRunning() {
        setupComputeUnderLockSuccess();
        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRun,
                aFuncStageRunId, aFuncStageRun
        ));
        executeWhatExecutorServiceHas();
        verify(functionalStageRunFactory, times(3)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(3)).updateState(any(), any());
        verify(stageRunStore, times(3)).compute(any(), any());
        verify(stageRunConnector).requestRun(any(), any(), any());

        Assertions.assertSame(newFuncStageRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage_andNewStatusIsPendingStart() {
        setupComputeUnderLockSuccess();
        when(newFuncStageRunAfterRequestAfterStateUpdate.getStatus()).thenReturn(OrchestrableContextStatus.PENDING_START);
        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRun,
                aFuncStageRunId, aFuncStageRun
        ));
        executeWhatExecutorServiceHas();

        verify(functionalStageRunFactory, times(3)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(3)).updateState(any(), any());
        verify(stageRunStore, times(3)).compute(any(), any());
        verify(stageRunConnector).requestRun(any(), any(), any());

        Assertions.assertSame(newFuncStageRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage_andNewStatusIsCancelled() {
        setupComputeUnderLockSuccess();
        when(newFuncStageRunAfterRequestAfterStateUpdate.getStatus()).thenReturn(OrchestrableContextStatus.CANCELLED);
        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRun,
                aFuncStageRunId, aFuncStageRun
        ));
        executeWhatExecutorServiceHas();

        verify(functionalStageRunFactory, times(3)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(3)).updateState(any(), any());
        verify(stageRunStore, times(3)).compute(any(), any());
        verify(stageRunConnector).requestRun(any(), any(), any());

        Assertions.assertSame(newFuncStageRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage_andNewStatusIsFailed() {
        setupComputeUnderLockSuccess();
        when(newFuncStageRunAfterRequestAfterStateUpdate.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);
        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRun,
                aFuncStageRunId, aFuncStageRun
        ));
        executeWhatExecutorServiceHas();

        verify(functionalStageRunFactory, times(3)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(3)).updateState(any(), any());
        verify(stageRunStore, times(3)).compute(any(), any());
        verify(stageRunConnector).requestRun(any(), any(), any());

        Assertions.assertSame(newFuncStageRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage_andNewStatusIsSuccess() {
        setupComputeUnderLockSuccess();
        when(newFuncStageRunAfterRequestAfterStateUpdate.getStatus()).thenReturn(OrchestrableContextStatus.SUCCESS);
        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRun,
                aFuncStageRunId, aFuncStageRun
        ));
        executeWhatExecutorServiceHas();

        verify(functionalStageRunFactory, times(3)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(3)).updateState(any(), any());
        verify(stageRunStore, times(3)).compute(any(), any());
        verify(stageRunConnector).requestRun(any(), any(), any());

        Assertions.assertSame(newFuncStageRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNoneToBeRequested() {
        setupComputeUnderLockSuccess();
        when(newFuncStageRunAfterStateUpdate.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of());

        verify(functionalStageRunFactory, times(1)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(1)).updateState(any(), any());
        verify(stageRunStore, times(1)).compute(any(), any());

        Assertions.assertSame(newFuncStageRunAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage_butTechnicalStageRequestFails() {
        setupComputeUnderLockSuccess();
        when(stageRunConnector.requestRun(any(), any(), any())).thenReturn(false);
        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRun,
                aFuncStageRunId, aFuncStageRun
        ));
        executeWhatExecutorServiceHas();

        verify(functionalStageRunFactory, times(3)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(3)).updateState(any(), any());
        verify(stageRunStore, times(3)).compute(any(), any());

        verify(stageRunConnector).requestRun(any(), any(), any());
        verify(technicalStageRunFactory).failed(eq(aTechStageRun), any());
        Assertions.assertSame(newFuncStageRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage_butExecutorServiceFailsAtAMoment() throws InterruptedException {
        setupComputeUnderLockSuccess();
        doThrow(RuntimeException.class).when(executorService).execute(any());

        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRun,
                aFuncStageRunId, aFuncStageRun
        ));
        executeWhatExecutorServiceHas();

        verify(functionalStageRunFactory, times(3)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(4)).updateState(any(), any());
        verify(functionalStageRunFactory, times(1)).updateState(any(), eq(OrchestrableContextStatus.CANCELLED));
        verify(stageRunStore, times(3)).compute(any(), any());
        verify(stageRunConnector).requestRun(any(), any(), any());

        Assertions.assertSame(newFuncStageRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage_butFunctionalStageServiceFailsAtAMoment() {
        setupComputeUnderLockSuccess();
        doThrow(RuntimeException.class).when(functionalStageService).getDefinitionId(any());
        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRun,
                aFuncStageRunId, aFuncStageRun
        ));
        executeWhatExecutorServiceHas();

        verify(functionalStageRunFactory, times(2)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(3)).updateState(any(), any());
        verify(functionalStageRunFactory, times(1)).updateState(any(), eq(OrchestrableContextStatus.CANCELLED));
        verify(stageRunStore, times(2)).compute(any(), any());
        verify(stageRunConnector).requestRun(any(), any(), any());

        Assertions.assertSame(newFuncStageRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage_ButNoAncestorGivesTheExpectedInput() {
        setupComputeUnderLockSuccess();
        when(aRunDependency.getInputId()).thenReturn("anotherOutputId");
        FunctionalStageRun result = stageRunService.computeStageRunUpdateUnderLock(stageRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRun,
                aFuncStageRunId, aFuncStageRun
        ));
        executeWhatExecutorServiceHas();

        verify(functionalStageRunFactory, times(2)).updateStageRunView(any(), any());
        verify(functionalStageRunFactory, times(3)).updateState(any(), any());
        verify(stageRunStore, times(2)).compute(any(), any());
        verify(stageRunConnector).requestRun(any(), any(), any());

        Assertions.assertSame(newFuncStageRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void getById_delegatesToStore() {
        setupGetById();
        StageRun<?> result = stageRunService.getById(aTechStageRunId);
        verify(stageRunStore).getById(aTechStageRunId);
        Assertions.assertSame(aTechStageRun, result);
    }

    @Test
    void requestCancel_whenTechnicalStage() {
        setupRequestCancelForSuccess();
        stageRunService.requestCancel(aTechStageRunId, executorId);
        verify(stageRunConnector).requestCancel(aTechStageRunId, executorId);
    }

    @Test
    void requestCancel_whenFunctionalStage() {
        setupRequestCancelForSuccess();
        stageRunService.requestCancel(aFuncStageRunId, null);
        verify(stageRunConnector).requestCancel(aTechStageRunInflightId, executorId);

        verify(aFuncStageRun).getInFlightStageRuns();
        verify(aFuncStageRunInflight).getInFlightStageRuns();
    }

    @Test
    void requestCancel_whenUnknownStageRunType() {
        setupRequestCancelForSuccess();
        StageRun unkownTypeStageRun = mock(StageRun.class);
        when(stageRunStore.getById("unkownStageRunId")).thenReturn(unkownTypeStageRun);
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class,
                () -> stageRunService.requestCancel("unkownStageRunId", executorId));

        assertEquals("Impossible, does not support following StageRun " + unkownTypeStageRun.getClass().getName(), exception.getMessage());
    }

    @Test
    void getNext() {

    }

    @Test
    void startRuns_whenTechnicalStage_andSucceededToRequestRun() {
        setupStartRunsSuccess();
        stageRunService.startRuns(List.of(aTechStageRun));

        verify(stageRunConnector).requestRun(aTechStageId, aTechStageRunId, Set.of());
        verify(technicalStageRunFactory).requested(aTechStageRun);
    }

    @Test
    void startRuns_whenTechnicalStage_andFailedToRequestRun() {
        setupStartRunsSuccess();
        when(stageRunConnector.requestRun(any(), any(), any())).thenReturn(false);
        stageRunService.startRuns(List.of(aTechStageRun));

        verify(stageRunConnector).requestRun(aTechStageId, aTechStageRunId, Set.of());
        verify(technicalStageRunFactory).failed(eq(aTechStageRun), any());
    }

    @Test
    void startRuns_whenFunctionalStage_andAllGood() {
        setupStarRunsForFunctionalStageRunForSuccess();
        stageRunService.startRuns(List.of(aFuncStageRun));
        executeWhatExecutorServiceHas();

        verify(stageRunStore).compute(eq(aFuncStageRunId), any());
    }

    @Test
    void startRuns_whenFunctionalStage_andExecutorSchedulingFailed() {
        setupStarRunsForFunctionalStageRunForSuccess();
        doThrow(RuntimeException.class).when(executorService).execute(any());

        stageRunService.startRuns(List.of(aFuncStageRun));

        verify(functionalStageRunFactory).updateState(aFuncStageRun, OrchestrableContextStatus.CANCELLED);
    }

    @Test
    void startRuns_whenFunctionalStage_andIssueWhenCallingDefinitionStore() {
        setupStarRunsForFunctionalStageRunForSuccess();
        doThrow(RuntimeException.class).when(functionalStageService).getDefinitionId(any());

        stageRunService.startRuns(List.of(aFuncStageRun));
        executeWhatExecutorServiceHas();

        verify(functionalStageRunFactory).updateState(aFuncStageRun, OrchestrableContextStatus.CANCELLED);
    }

    @Test
    void startRuns_whenUnknownStageRunType() {
        setupStartRunsSuccess();
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> stageRunService.startRuns(List.of(mock(StageRun.class)))
        );

        assertEquals("Do not support other type than both up there. Please override this method.", exception.getMessage());
    }

    @Test
    void getNextStageRuns_thenSuccess() {
        setupNextStageRunsSuccess();
        stageRunService.getNextStageRuns(aRunContext, Map.of(aRootContextId, Set.of(aRunDependency)));

        verify(functionalStageRunFactory).create(any(),any(),any(),any());
        verify(stageRunStore).put(aFunctionalStageRunId, anOtherFunctionalStageRun);
    }

    @Test
    void getNextStageRuns_whenItsTechnicalStage_thenSuccess() {
        setupNextStageRunsSuccess();
        when(functionalStageService.getDefinitionId(any())).thenReturn(null);
        stageRunService.getNextStageRuns(aRunContext, Map.of(aRootContextId, Set.of(aRunDependency)));

        verify(technicalStageRunFactory).toBeRequested(any(),any(),any());
        verify(stageRunStore).put(aTechnicalStageRunId, anOtherTechnicalStageRun);
    }

    @Test
    void getNextStageRuns_whenAlreadyhaveStageAncestors(){
        setupNextStageRunsSuccess();
        when(aRunDependency.getAncestors()).thenReturn(Set.of(mock(StageRunAncestor.class)));
        stageRunService.getNextStageRuns(aRunContext, Map.of(aRootContextId, Set.of(aRunDependency)));

        verify(stageRunStore).put(aFunctionalStageRunId, anOtherFunctionalStageRun);
    }

    @Test
    void put_delegatesToStore() {
        stageRunService.put(aTechStageRunId, aTechStageRun);
        verify(stageRunStore).put(aTechStageRunId, aTechStageRun);
    }

    private void setupRequestCancelForSuccess() {
        setupGetById();

        when(aFuncStageRun.getInFlightStageRuns()).thenReturn(Set.of(aFuncStageRunInflight, aTechStageRunInflight));
        when(aFuncStageRunInflight.getStageRunId()).thenReturn(aFuncStageRunInflightId);
        when(aTechStageRunInflight.getStageRunId()).thenReturn(aTechStageRunInflightId);
        when(aTechStageRunInflight.getExecutorId()).thenReturn(executorId);
        when(aFuncStageRunInflight.getInFlightStageRuns()).thenReturn(Set.of());
    }

    private void setupGetById() {
        when(stageRunStore.getById(aTechStageRunId)).thenReturn((StageRun) aTechStageRun);
        when(stageRunStore.getById(aFuncStageRunId)).thenReturn((StageRun) aFuncStageRun);
        when(stageRunStore.getById(aTechStageRunInflightId)).thenReturn((StageRun) aTechStageRunInflight);
        when(stageRunStore.getById(aFuncStageRunInflightId)).thenReturn((StageRun) aFuncStageRunInflight);
    }

    private void setupComputeUnderLockSuccess() {
        when(functionalStageRunFactory.updateStageRunView(any(), any())).thenReturn(newFuncStageRun, newFuncStageRunAfterRequest);
        when(functionalStageRunFactory.updateState(any(), any())).thenReturn(newFuncStageRunAfterStateUpdate, newFuncStageRunAfterRequestAfterStateUpdate);
        doCallRealMethod().when(stageRunStore).compute(any(), any());

        when(aTechStageRun.getStatus()).thenReturn(TO_BE_REQUESTED);
        when(aFuncStageRun.getStatus()).thenReturn(OrchestrableContextStatus.NEW);

        when(newFuncStageRunAfterRequestAfterStateUpdate.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        setupStartRunsSuccess();
    }

    private void setupStarRunsForFunctionalStageRunForSuccess() {
        setupStartRunsSuccess();
        setupComputeUnderLockSuccess();
    }

    private void setupStartRunsSuccess() {
        setupStartTechnicalStageSuccess();
        setupStartFunctionalStageSuccess();
    }

    private void setupStartFunctionalStageSuccess() {
        when(aFuncStageRun.getExecutionGraph()).thenReturn(executionGraph);
        when(aFuncStageRun.getContextId()).thenReturn(aFuncStageRunId);
        when(executionGraph.getRootsIds()).thenReturn(Set.of(aRootContextId));
        ContextualizedStageDependency aContextStageDependency = mock(ContextualizedStageDependency.class);
        when(executionGraph.getFlowStageDependencies(aRootContextId)).thenReturn(Set.of(aContextStageDependency));
        when(aContextStageDependency.getInputId()).thenReturn("inputId");
        when(aFuncStageRun.toRunContext()).thenReturn(aRunContext);

        setupNextStageRunsSuccess();
    }

    private void setupNextStageRunsSuccess() {
        when(aRunContext.getStageRunDependencies()).thenReturn(Set.of(aRunDependency));
        when(aRunDependency.getInputId()).thenReturn("inputId");
        when(aRootContextId.getStageId()).thenReturn("stageId");
        when(functionalStageService.getDefinitionId("stageId")).thenReturn("stageDefinitionId");
        when(anOtherTechnicalStageRun.getStageRunId()).thenReturn("aTechnicalStageRunId");
        when(technicalStageRunFactory.toBeRequested(eq(aRunContext), eq(aRootContextId), any())).thenReturn(anOtherTechnicalStageRun);
        FunctionalStageDefinition functionalStageDefinition = mock(FunctionalStageDefinition.class);
        when(functionalStageDefinitionService.getById("stageDefinitionId")).thenReturn(functionalStageDefinition);
        when(anOtherFunctionalStageRun.getStageRunId()).thenReturn(aFunctionalStageRunId);
        when(anOtherFunctionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.CANCELLED);
        when(functionalStageRunFactory.create(any(), any(), any(), any())).thenReturn(anOtherFunctionalStageRun);
    }

    private void setupStartTechnicalStageSuccess() {
        when(aTechStageRun.getContextualizedStageId()).thenReturn(aTechStageContextId);
        when(aTechStageContextId.getStageId()).thenReturn(aTechStageId);
        when(aTechStageRun.getStageRunId()).thenReturn(aTechStageRunId);
        when(stageRunConnector.requestRun(any(), any(), any())).thenReturn(true);
        when(technicalStageRunFactory.requested(aTechStageRun)).thenReturn(aTechStageRun);
        when(technicalStageRunFactory.failed(eq(aTechStageRun), any())).thenReturn(aTechStageRun);
    }

    private void executeWhatExecutorServiceHas() {
        ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(executorService).execute(captor.capture());
        captor.getValue().run();
    }

    private abstract class DummyStageRunStore implements StageRunStore {
        @Override
        public FunctionalStageRun compute(String contextId, BiFunction<String, FunctionalStageRun, FunctionalStageRun> valueComputer) {
            FunctionalStageRun functionalStageRun = mock(FunctionalStageRun.class);
            when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.NEW);
            return valueComputer.apply(contextId, functionalStageRun);
        }
    }
}