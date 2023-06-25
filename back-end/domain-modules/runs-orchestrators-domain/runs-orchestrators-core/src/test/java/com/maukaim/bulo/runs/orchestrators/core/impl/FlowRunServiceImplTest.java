package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.exceptions.FlowRunStartException;
import com.maukaim.bulo.runs.orchestrators.core.factories.FlowRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.utils.FlowUtils;
import com.maukaim.bulo.runs.orchestrators.core.utils.OrchestrableContextStatusResolver;
import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;

import static com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRunStatus.TO_BE_REQUESTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class FlowRunServiceImplTest {
    private final FlowService flowService = mock(FlowService.class);
    private final DummyFlowRunStore flowRunStore = mock(DummyFlowRunStore.class);
    private final StageRunService stageRunService = mock(StageRunService.class);
    private final FlowUtils flowUtils = mock(FlowUtils.class);
    private final FlowRunFactory flowRunFactory = mock(FlowRunFactory.class);
    private final OrchestrableContextStatusResolver orchestrableContextStatusResolver = mock(OrchestrableContextStatusResolver.class);

    private final String flowRunId = "flowRunId";
    private final String flowId = "flowId";
    private FlowRunService flowRunService;

    private final FlowRun flowRun = mock(FlowRun.class);
    private final FlowRun newFlowRun = mock(FlowRun.class);
    private final FlowRun newFlowRunAfterStateUpdate = mock(FlowRun.class);
    private final FlowRun newFlowRunAfterRequest = mock(FlowRun.class);
    private final FlowRun newFlowRunAfterRequestAfterStateUpdate = mock(FlowRun.class);
    private final FlowRun flowRunToBePersisted = mock(FlowRun.class);

    private final ContextStageId rootContextStageId = mock(ContextStageId.class);

    private final Flow flow = mock(Flow.class);
    private final TechnicalStageRun aTechStageRunToUpdate = mock(TechnicalStageRun.class);
    private final String aTechStageRunId = "aTechStageRunId";
    private final FunctionalStageRun aFuncStageRunToUpdate = mock(FunctionalStageRun.class);
    private final String aFuncStageRunId = "aFuncStageRunId";

    @BeforeEach
    void init() {
        flowRunService = new FlowRunServiceImpl(flowService, flowRunStore, stageRunService, flowUtils, flowRunFactory, orchestrableContextStatusResolver);
    }

    @Test
    void getById_delegatesToFlowRunStore() {
        FlowRun flowRun = mock(FlowRun.class);
        when(flowRunStore.getRun(flowRunId)).thenReturn(flowRun);

        FlowRun result = flowRunService.getById(flowRunId);

        verify(flowRunStore).getRun(flowRunId);
        assertSame(flowRun, result);
    }

    @Test
    void getByFlowId_delegatesToFlowRunStore() {
        List<FlowRun> flowRuns = List.of(mock(FlowRun.class));
        when(flowRunStore.getByFlowId(flowId)).thenReturn(flowRuns);

        List<FlowRun> result = flowRunService.getByFlowId(flowId);

        verify(flowRunStore).getByFlowId(flowId);
        assertSame(flowRuns, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNeedToRequestBothStage() {
        setupComputeUnderLockSuccess();
        FlowRun result = flowRunService.computeStageRunUpdateUnderLock(flowRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRunToUpdate,
                aFuncStageRunId, aFuncStageRunToUpdate
        ));
        verify(flowRunFactory, times(2)).updateStageRunView(any(), any());
        verify(flowRunFactory, times(2)).updateState(any(), any());
        verify(flowRunStore, times(2)).compute(any(), any());
        verify(stageRunService).startRuns(argThat((arg) -> arg.containsAll(
                        List.of(aTechStageRunToUpdate, aFuncStageRunToUpdate)
                ))
        );
        Assertions.assertSame(newFlowRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenOnlyTechStageNeedToBeRequested() {
        setupComputeUnderLockSuccess();
        FlowRun result = flowRunService.computeStageRunUpdateUnderLock(flowRunId, (flowRun) -> Map.of(
                aTechStageRunId, aTechStageRunToUpdate
        ));
        verify(flowRunFactory, times(2)).updateStageRunView(any(), any());
        verify(flowRunFactory, times(2)).updateState(any(), any());
        verify(flowRunStore, times(2)).compute(any(), any());
        verify(stageRunService).startRuns(argThat((arg) -> arg.containsAll(
                        List.of(aTechStageRunToUpdate)
                ))
        );
        Assertions.assertSame(newFlowRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenOnlyFuncStageNeedToBeRequested() {
        setupComputeUnderLockSuccess();
        FlowRun result = flowRunService.computeStageRunUpdateUnderLock(flowRunId, (flowRun) -> Map.of(
                aFuncStageRunId, aFuncStageRunToUpdate
        ));
        verify(flowRunFactory, times(2)).updateStageRunView(any(), any());
        verify(flowRunFactory, times(2)).updateState(any(), any());
        verify(flowRunStore, times(2)).compute(any(), any());
        verify(stageRunService).startRuns(argThat((arg) -> arg.containsAll(
                        List.of(aFuncStageRunToUpdate)
                ))
        );
        Assertions.assertSame(newFlowRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void computeStageRunUpdateUnderLock_whenNoNeedToRequestAnyStage() {
        setupComputeUnderLockSuccess();
        FlowRun result = flowRunService.computeStageRunUpdateUnderLock(flowRunId, (flowRun) -> Map.of(
        ));
        verify(flowRunFactory, times(1)).updateStageRunView(any(), any());
        verify(flowRunFactory, times(1)).updateState(any(), any());
        verify(flowRunStore, times(1)).compute(any(), any());
        verify(stageRunService, never()).startRuns(any());
        Assertions.assertSame(newFlowRunAfterStateUpdate, result);
    }

    @Test
    void startRun_whenProvidedAllRootStagesToStart_thenSuccess(){
        setupStartRunSuccess();
        FlowRun result = flowRunService.startRun(flowId, Set.of(rootContextStageId));

        verify(flowUtils).getRootIds(flow);
        verify(flowRunStore).add(flowRun);

        verify(flowRunFactory, times(2)).updateStageRunView(any(), any());
        verify(flowRunFactory, times(2)).updateState(any(), any());
        verify(flowRunStore, times(2)).compute(any(), any());
        verify(stageRunService).startRuns(argThat((arg) -> arg.containsAll(
                        List.of(aTechStageRunToUpdate, aFuncStageRunToUpdate)
                ))
        );
        Assertions.assertSame(newFlowRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void startRun_whenNoRootStageIdsProvided_thenSuccess(){
        setupStartRunSuccess();
        FlowRun result = flowRunService.startRun(flowId, Set.of());

        verify(flowUtils).getRootIds(flow);
        verify(flowRunStore).add(flowRun);

        verify(flowRunFactory, times(2)).updateStageRunView(any(), any());
        verify(flowRunFactory, times(2)).updateState(any(), any());
        verify(flowRunStore, times(2)).compute(any(), any());
        verify(stageRunService).startRuns(argThat((arg) -> arg.containsAll(
                        List.of(aTechStageRunToUpdate, aFuncStageRunToUpdate)
                ))
        );
        Assertions.assertSame(newFlowRunAfterRequestAfterStateUpdate, result);
    }

    @Test
    void startRun_whenRequestedWithNonRootStage_thenThrowException(){
        setupStartRunSuccess();
        Set<ContextStageId> rootStageIds = Set.of(rootContextStageId, mock(ContextStageId.class));
        FlowRunStartException exception = Assertions.assertThrows(FlowRunStartException.class,
                () -> flowRunService.startRun(flowId, rootStageIds));

        assertEquals("Flow ["+flow.getFlowId()+"] does not recognize one of the FlowStages "+ rootStageIds +" as a root.", exception.getMessage());
    }

    @Test
    void startRun_whenFlowServiceDoesNotHaveFlowForFlowId_thenThrowException(){
        setupStartRunSuccess();
        when(flowService.getFlow(flowId)).thenReturn(Optional.empty());
        FlowRunStartException exception = Assertions.assertThrows(FlowRunStartException.class,
                () -> flowRunService.startRun(flowId, Set.of(rootContextStageId)));

        assertEquals("No flow existing with id: " + flowId , exception.getMessage());
    }

    //TODO: Prep success scenario startRun and decline in multiple sub scenario
    private void setupStartRunSuccess(){
        setupComputeUnderLockSuccess();
        when(flowService.getFlow(flowId)).thenReturn(Optional.of(flow));
        when(flowUtils.getRootIds(flow)).thenReturn(Set.of(rootContextStageId));
        when(flowRunFactory.create(flow)).thenReturn(flowRun);
        when(flowRunStore.add(flowRun)).thenReturn(flowRun);

        when(stageRunService.getNextStageRuns(any(), any())).thenReturn(Map.of(
                aTechStageRunId, aTechStageRunToUpdate,
                aFuncStageRunId, aFuncStageRunToUpdate
        ));

    }

    private void setupComputeUnderLockSuccess() {
        when(flowRunFactory.updateStageRunView(any(), any())).thenReturn(newFlowRun, newFlowRunAfterRequest);
        when(flowRunFactory.updateState(any(), any())).thenReturn(newFlowRunAfterStateUpdate, newFlowRunAfterRequestAfterStateUpdate);
        doCallRealMethod().when(flowRunStore).compute(any(), any());

        when(aTechStageRunToUpdate.getStatus()).thenReturn(TO_BE_REQUESTED);
        when(aFuncStageRunToUpdate.getStatus()).thenReturn(OrchestrableContextStatus.NEW);
        when(stageRunService.startRuns(argThat((arg) -> arg.containsAll(
                        List.of(aTechStageRunToUpdate, aFuncStageRunToUpdate)
                ))
        )).thenReturn(Map.of(
                aTechStageRunId, aTechStageRunToUpdate,
                aFuncStageRunId, aFuncStageRunToUpdate
        ));
    }

    private abstract class DummyFlowRunStore implements FlowRunStore {
        @Override
        public FlowRun compute(String contextId, BiFunction<String, FlowRun, FlowRun> valueComputer) {
            //just execute the inside, dummy compute
            return valueComputer.apply(contextId, mock(FlowRun.class));
        }
    }


}