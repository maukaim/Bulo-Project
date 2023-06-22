package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.StageRunEventProcessorTest;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRunStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RunFailedStageRunEventProcessorTest extends StageRunEventProcessorTest<RunFailedStageRunEventProcessor> {

    private final String executorId = "executorId";
    private final String anotherFunctionalStageId = "anotherFunctionalStageId";
    private final String anotherTechnicalStageId = "anotherTechnicalStageId";
    private final String aSecondOtherTechnicalStageId = "aSecondOtherTechnicalStageId";
    private final FunctionalStageRun anotherFunctionalStageRun = mock(FunctionalStageRun.class);
    private final TechnicalStageRun anotherTechnicalStageRun = mock(TechnicalStageRun.class);
    private final TechnicalStageRun aSecondOtherTechnicalStageRun = mock(TechnicalStageRun.class);

    @Override
    protected RunFailedStageRunEventProcessor getStageRunEventProcessor() {
        return new RunFailedStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
    }

    @BeforeEach
    void init() {
        when(flowRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsFunctional.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsTechnical.getStatus()).thenReturn(TechnicalStageRunStatus.RUNNING);

        when(technicalStageRunFactory.failed(any(), any())).thenReturn(mock(TechnicalStageRun.class));
        when(technicalStageRunFactory.toBeCancelled(any())).thenReturn(mock(TechnicalStageRun.class));
        when(functionalStageRunFactory.updateState(any(), any())).thenReturn(mock(FunctionalStageRun.class));

        when(flowRun.getInFlightStageRuns()).thenReturn(Set.of(stageRunAsTechnical));
        when(anotherFunctionalStageRun.getStageRunId()).thenReturn(anotherFunctionalStageId);
        when(anotherTechnicalStageRun.getStageRunId()).thenReturn(anotherTechnicalStageId);
        when(aSecondOtherTechnicalStageRun.getStageRunId()).thenReturn(aSecondOtherTechnicalStageId);

        when(anotherTechnicalStageRun.getStatus()).thenReturn(TechnicalStageRunStatus.RUNNING);
        when(aSecondOtherTechnicalStageRun.getStatus()).thenReturn(TechnicalStageRunStatus.TO_BE_CANCELLED);
        when(anotherTechnicalStageRun.getExecutorId()).thenReturn(executorId);
        when(aSecondOtherTechnicalStageRun.getExecutorId()).thenReturn(executorId);
    }


    @Test
    void processFlowRunContext_whenTechnicalStageRun() {
        setUpInflightsWith(flowRun, stageRunAsTechnical);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);
        executeFlowRunContextProcess();

        verify(stageRunService).requestCancel(anotherTechnicalStageId,executorId);
        verify(stageRunService, never()).requestCancel(aSecondOtherTechnicalStageId,executorId);
        verify(stageRunService).requestCancel(anotherFunctionalStageId, null);
        verify(stageRunService, never()).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFlowRunContext_whenFunctionalStageRun() {
        setUpInflightsWith(flowRun, stageRunAsFunctional);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);
        executeFlowRunContextProcess();

        verify(stageRunService).requestCancel(anotherTechnicalStageId,executorId);
        verify(stageRunService, never()).requestCancel(aSecondOtherTechnicalStageId,executorId);
        verify(stageRunService).requestCancel(anotherFunctionalStageId, null);
        verify(stageRunService).requestCancel(stageRunId, null);
    }

    @Test
    void processFunctionalStageRunContext_whenTechnicalStageRun() {
        setUpInflightsWith(functionalStageRun, stageRunAsTechnical);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);
        executeFunctionalStageRunContextProcess();

        verify(stageRunService).requestCancel(anotherTechnicalStageId,executorId);
        verify(stageRunService, never()).requestCancel(aSecondOtherTechnicalStageId,executorId);
        verify(stageRunService).requestCancel(anotherFunctionalStageId, null);
        verify(stageRunService, never()).requestCancel(stageRunId, executorId);
    }

    @Test
    void processFunctionalStageRunContext_whenFunctionalStageRun() {
        setUpInflightsWith(functionalStageRun, stageRunAsFunctional);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);
        executeFunctionalStageRunContextProcess();

        verify(stageRunService).requestCancel(anotherTechnicalStageId,executorId);
        verify(stageRunService, never()).requestCancel(aSecondOtherTechnicalStageId,executorId);
        verify(stageRunService).requestCancel(anotherFunctionalStageId, null);
        verify(stageRunService).requestCancel(stageRunId, null);
    }

    private void setUpInflightsWith(OrchestrableRunContext<?> ctx, StageRun<?> stageRun) {
        when(ctx.getInFlightStageRuns()).thenReturn(Set.of(
                stageRun,
                anotherFunctionalStageRun,
                anotherTechnicalStageRun,
                aSecondOtherTechnicalStageRun
        ));
    }

    private Map<String, StageRun<?>> executeFlowRunContextProcess() {
        stageRunEventProcessor.process(stageRunId, Instant.EPOCH, flowRunContext);
        Function<FlowRun, Map<String, StageRun<?>>> computeStage = getFunctionPassedToFlowRunService();
        return computeStage.apply(flowRun);
    }

    private Map<String, StageRun<?>> executeFunctionalStageRunContextProcess() {
        stageRunEventProcessor.process(stageRunId, Instant.EPOCH, functionalStageRunContext);
        Function<FunctionalStageRun, Map<String, StageRun<?>>> computeStage = getFunctionPassedToStageRunService();
        return computeStage.apply(functionalStageRun);
    }
}