package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.StageRunEventProcessorTest;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RunCancelledStageRunEventProcessorTest extends StageRunEventProcessorTest<RunCancelledStageRunEventProcessor> {
    private final Instant instant = Instant.EPOCH;

    private final FunctionalStageRun anotherFsr = mock(FunctionalStageRun.class);
    private final TechnicalStageRun anotherTsr = mock(TechnicalStageRun.class);

    @Override
    protected RunCancelledStageRunEventProcessor getStageRunEventProcessor() {
        return new RunCancelledStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
    }

    @BeforeEach
    void init() {
        when(flowRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsFunctional.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsTechnical.getStatus()).thenReturn(TechnicalStageRunStatus.RUNNING);

        when(anotherFsr.getStageRunId()).thenReturn("anotherFsrId");
        when(anotherTsr.getStageRunId()).thenReturn("anotherTsrId");
        when(anotherFsr.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(anotherTsr.getStatus()).thenReturn(TechnicalStageRunStatus.RUNNING);
        when(technicalStageRunFactory.toBeCancelled(anotherTsr)).thenReturn(mock(TechnicalStageRun.class));

        when(flowRun.getInFlightStageRuns()).thenReturn(Set.of(anotherFsr, anotherTsr, stageRunAsTechnical));
        when(functionalStageRun.getInFlightStageRuns()).thenReturn(Set.of(anotherFsr, anotherTsr, stageRunAsTechnical));

        when(technicalStageRunFactory.cancelled(stageRunAsTechnical, instant)).thenReturn(mock(TechnicalStageRun.class));
        when(functionalStageRunFactory.updateState(stageRunAsFunctional, OrchestrableContextStatus.CANCELLED)).thenReturn(mock(FunctionalStageRun.class));
    }

    @Test
    void processFlowRunContext_whenTechnicalStageRun_thenSuccess() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);
        executeFlowRunContextProcess();

        verify(technicalStageRunFactory).cancelled(any(),any());
        verify(stageRunService, times(2)).requestCancel(any(),any());
    }

    @Test
    void processFlowRunContext_whenFunctionalStageRun_thenSuccess() {
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);
        executeFunctionalStageRunContextProcess();

        verify(functionalStageRunFactory).updateState(any(),any());
        verify(stageRunService, times(2)).requestCancel(any(),any());
    }


    private Map<String, StageRun<?>> executeFlowRunContextProcess() {
        stageRunEventProcessor.process(stageRunId, instant, flowRunContext);
        Function<FlowRun, Map<String, StageRun<?>>> computeStage = getFunctionPassedToFlowRunService();
        return computeStage.apply(flowRun);
    }

    private Map<String, StageRun<?>> executeFunctionalStageRunContextProcess() {
        stageRunEventProcessor.process(stageRunId, instant, functionalStageRunContext);
        Function<FunctionalStageRun, Map<String, StageRun<?>>> computeStage = getFunctionPassedToStageRunService();
        return computeStage.apply(functionalStageRun);
    }
}