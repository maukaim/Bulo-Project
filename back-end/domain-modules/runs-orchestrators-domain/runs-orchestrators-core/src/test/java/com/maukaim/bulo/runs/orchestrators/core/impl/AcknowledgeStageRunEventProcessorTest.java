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

import java.util.Map;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AcknowledgeStageRunEventProcessorTest extends StageRunEventProcessorTest<AcknowledgeStageRunEventProcessor> {
    private final String executorId = "executorId";


    @Override
    protected AcknowledgeStageRunEventProcessor getStageRunEventProcessor() {
        return new AcknowledgeStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
    }

    @BeforeEach
    void init(){
        when(flowRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsFunctional.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsTechnical.getStatus()).thenReturn(TechnicalStageRunStatus.RUNNING);

        when(technicalStageRunFactory.acknowledged(any(), any())).thenReturn(mock(TechnicalStageRun.class));
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

    @Test
    void processFlowRunContext_whenTechnicalStageRun_andOrchestrableRunContextStatusIsProblem_butStageRunIsnTerminal_thenCancel() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);

        when(flowRun.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);
        when(stageRunAsTechnical.getStatus()).thenReturn(TechnicalStageRunStatus.TO_BE_REQUESTED);

        executeFlowRunContextProcess();
        verify(stageRunService).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFlowRunContext_whenFunctionalStageRun_andOrchestrableRunContextStatusIsProblem_butStageRunIsnTerminal_thenCancel() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);

        when(flowRun.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);
        when(stageRunAsFunctional.getStatus()).thenReturn(OrchestrableContextStatus.PENDING_START);

        executeFlowRunContextProcess();
        verify(stageRunService).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFunctionalStageRunContext_whenTechnicalStageRun_andOrchestrableRunContextStatusIsProblem_butStageRunIsnTerminal_thenCancel() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);

        when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);
        when(stageRunAsTechnical.getStatus()).thenReturn(TechnicalStageRunStatus.TO_BE_REQUESTED);

        executeFunctionalStageRunContextProcess();
        verify(stageRunService).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFunctionalStageRunContext_whenFunctionalStageRun_andOrchestrableRunContextStatusIsProblem_butStageRunIsnTerminal_thenCancel() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);

        when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.FAILED);
        when(stageRunAsFunctional.getStatus()).thenReturn(OrchestrableContextStatus.PENDING_START);

        executeFunctionalStageRunContextProcess();
        verify(stageRunService).requestCancel(stageRunId, executorId);
    }


        @Test
    void processFlowRunContext_whenStageRunIdDoesNotMatchAnyStageInStore_thenThrow() {
        getActualRunExceptionSetup();
        Assertions.assertThrows(IllegalArgumentException.class, this::executeFunctionalStageRunContextProcess);

        verify(stageRunService, never()).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFunctionalStageRunContext_whenStageRunIdDoesNotMatchAnyStageInStore_thenThrow() {
        getActualRunExceptionSetup();
        Assertions.assertThrows(IllegalArgumentException.class, this::executeFunctionalStageRunContextProcess);

        verify(stageRunService, never()).requestCancel(stageRunId, executorId);
    }

    private Map<String, StageRun<?>> executeFlowRunContextProcess(){
        stageRunEventProcessor.process(stageRunId, executorId, flowRunContext);
        Function<FlowRun, Map<String, StageRun<?>>> computeStage = getFunctionPassedToFlowRunService();
        return computeStage.apply(flowRun);
    }

    private Map<String, StageRun<?>> executeFunctionalStageRunContextProcess(){
        stageRunEventProcessor.process(stageRunId, executorId, functionalStageRunContext);
        Function<FunctionalStageRun, Map<String, StageRun<?>>> computeStage = getFunctionPassedToStageRunService();
        return computeStage.apply(functionalStageRun);
    }
}