package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.StageRunEventProcessorTest;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRunStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StartRunStageRunEventProcessorTest extends StageRunEventProcessorTest<StartRunStageRunEventProcessor> {
    private final String executorId = "executorId";

    @Override
    protected StartRunStageRunEventProcessor getStageRunEventProcessor() {
        return new StartRunStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
    }

    @BeforeEach
    void init(){
        when(flowRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsFunctional.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsTechnical.getStatus()).thenReturn(TechnicalStageRunStatus.RUNNING);
        when(stageRunAsTechnical.getExecutorId()).thenReturn(executorId);

        when(technicalStageRunFactory.launched(any(), any())).thenReturn(mock(TechnicalStageRun.class));
        when(functionalStageRunFactory.updateState(any(), any())).thenReturn(mock(FunctionalStageRun.class));
    }

    @Test
    void processFlowRunContext_whenTechnicalStageRun_thenSuccess() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);
        executeFlowRunContextProcess();
        verify(stageRunService, never()).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFlowRunContext_whenFunctionalStageRun_thenSuccess() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);
        executeFlowRunContextProcess();
        verify(stageRunService, never()).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFunctionalStageRunContext_whenTechnicalStageRun_thenSuccess() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);
        executeFunctionalStageRunContextProcess();
        verify(stageRunService, never()).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFunctionalStageRunContext_whenFunctionalStageRun_thenSuccess() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);
        executeFunctionalStageRunContextProcess();
        verify(stageRunService, never()).requestCancel(stageRunId, executorId);
    }
    ////

    @Test
    void processFlowRunContext_whenTechnicalStageRun_andContextStatusIsProblem_andActualRunIsNotTerminal_thenRequestCancel() {
        when(flowRun.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);
        executeFlowRunContextProcess();
        verify(stageRunService).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFlowRunContext_whenFunctionalStageRun_andContextStatusIsProblem_andActualRunIsNotTerminal_thenRequestCancel() {
        when(flowRun.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);
        executeFlowRunContextProcess();
        verify(stageRunService).requestCancel(stageRunId, null);
    }

    @Test
    void processFunctionalStageRunContext_whenTechnicalStageRun_andContextStatusIsProblem_andActualRunIsNotTerminal_thenRequestCancel() {
        when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);
        executeFunctionalStageRunContextProcess();
        verify(stageRunService).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFunctionalStageRunContext_whenFunctionalStageRun_andContextStatusIsProblem_andActualRunIsNotTerminal_thenRequestCancel() {
        when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);
        executeFunctionalStageRunContextProcess();
        verify(stageRunService).requestCancel(stageRunId, null);
    }

    private Map<String, StageRun<?>> executeFlowRunContextProcess(){
        stageRunEventProcessor.process(stageRunId, Instant.EPOCH, flowRunContext);
        Function<FlowRun, Map<String, StageRun<?>>> computeStage = getFunctionPassedToFlowRunService();
        return computeStage.apply(flowRun);
    }

    private Map<String, StageRun<?>> executeFunctionalStageRunContextProcess(){
        stageRunEventProcessor.process(stageRunId, Instant.EPOCH, functionalStageRunContext);
        Function<FunctionalStageRun, Map<String, StageRun<?>>> computeStage = getFunctionPassedToStageRunService();
        return computeStage.apply(functionalStageRun);
    }
}
