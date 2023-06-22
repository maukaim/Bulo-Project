package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.core.StageRunEventProcessorTest;
import com.maukaim.bulo.runs.orchestrators.core.utils.OrchestrableUtils;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRunStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RunSuccessfulStageRunEventProcessorTest extends StageRunEventProcessorTest<RunSuccessfulStageRunEventProcessor> {
    private final OrchestrableUtils orchestrableUtils = mock(OrchestrableUtils.class);
    private final ExecutionGraph executionGraph = mock(ExecutionGraph.class);

    private final ContextStageId actualRunContextStageId = mock(ContextStageId.class);
    private final ContextStageId childContextStageId = mock(ContextStageId.class);

    @Override
    protected RunSuccessfulStageRunEventProcessor getStageRunEventProcessor() {
        return new RunSuccessfulStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory, orchestrableUtils);
    }

    @BeforeEach
    void init() {
        when(flowRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(functionalStageRun.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsFunctional.getStatus()).thenReturn(OrchestrableContextStatus.RUNNING);
        when(stageRunAsTechnical.getStatus()).thenReturn(TechnicalStageRunStatus.RUNNING);

        when(stageRunAsTechnical.getContextualizedStageId()).thenReturn(actualRunContextStageId);
        when(stageRunAsFunctional.getContextualizedStageId()).thenReturn(actualRunContextStageId);

        when(technicalStageRunFactory.success(any(), any())).thenReturn(mock(TechnicalStageRun.class));
        when(technicalStageRunFactory.toBeCancelled(any())).thenReturn(mock(TechnicalStageRun.class));
        when(functionalStageRunFactory.updateState(any(), any())).thenReturn(mock(FunctionalStageRun.class));
        when(orchestrableUtils.getRunDependencies(any(), any(), any())).thenReturn(Set.of(mock(RunDependency.class)));
        when(stageRunService.getNextStageRuns(any(), any())).thenReturn(Map.of("childrenId", mock(StageRun.class)));
    }

    @Test
    void processFlowRunContext_whenTechnicalStageRun() {
        setupChildrenAndStageRunIds(flowRun, stageRunAsTechnical);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);

        Map<String, StageRun<?>> result = executeFlowRunContextProcess();

        Assertions.assertEquals(2, result.size());
        verify(technicalStageRunFactory).success(stageRunAsTechnical, Instant.EPOCH);
        verify(orchestrableUtils).getRunDependencies(eq(childContextStageId), any(), any());
    }

    @Test
    void processFlowRunContext_whenFunctionalStageRun() {
        setupChildrenAndStageRunIds(flowRun, stageRunAsFunctional);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);

        Map<String, StageRun<?>> result = executeFlowRunContextProcess();

        Assertions.assertEquals(2, result.size());
        verify(functionalStageRunFactory).updateState(stageRunAsFunctional, OrchestrableContextStatus.SUCCESS);
        verify(orchestrableUtils).getRunDependencies(eq(childContextStageId), any(), any());
    }

    @Test
    void processFunctionalStageRunContext_whenTechnicalStageRun() {
        setupChildrenAndStageRunIds(functionalStageRun, stageRunAsTechnical);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsTechnical);

        Map<String, StageRun<?>> result = executeFunctionalStageRunContextProcess();

        Assertions.assertEquals(2, result.size());
        verify(technicalStageRunFactory).success(stageRunAsTechnical, Instant.EPOCH);
        verify(orchestrableUtils).getRunDependencies(eq(childContextStageId), any(), any());
    }

    @Test
    void processFunctionalStageRunContext_whenFunctionalStageRun() {
        setupChildrenAndStageRunIds(functionalStageRun, stageRunAsFunctional);
        when(stageRunService.getById(stageRunId)).thenReturn((StageRun) stageRunAsFunctional);

        Map<String, StageRun<?>> result = executeFunctionalStageRunContextProcess();

        Assertions.assertEquals(2, result.size());
        verify(functionalStageRunFactory).updateState(stageRunAsFunctional, OrchestrableContextStatus.SUCCESS);
        verify(orchestrableUtils).getRunDependencies(eq(childContextStageId), any(), any());
    }

    private void setupChildrenAndStageRunIds(OrchestrableRunContext<?> ctx, StageRun<?> actualStageRun){
        when(ctx.getExecutionGraph()).thenReturn(executionGraph);
        when(executionGraph.getChildren(actualStageRun.getContextualizedStageId())).thenReturn(Set.of(
                childContextStageId
        ));
        when(ctx.otherAncestorsAreSuccessful(childContextStageId, actualRunContextStageId)).thenReturn(true);


        String id = actualStageRun.getStageRunId();
        when(ctx.getStageRunIds()).thenReturn(Set.of(id));
        when(stageRunService.getById(id)).thenReturn((StageRun)actualStageRun);
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