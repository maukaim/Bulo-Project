package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FlowRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class StartRunStageRunEventProcessor extends StageRunEventProcessor {

    public StartRunStageRunEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService, stageRunService);
    }

    public void process(String stageRunId, Instant instant, FunctionalStageRunContext context) {
        System.out.println("Run Started event... " + stageRunId);
        stageRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFunctionalStageRun) -> commonProcess(actualFunctionalStageRun, stageRunId, instant));
    }

    public void process(String stageRunId, Instant instant, FlowRunContext context) {
        System.out.println("Run Started event... " + stageRunId);
        flowRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFlowRun) -> commonProcess(actualFlowRun, stageRunId, instant));
    }

    private Map<String, StageRun<?>> commonProcess(OrchestrableRunContext<?> orchestrableRunContext, String stageRunId, Instant instant) {
        StageRun<?> actualRun = getActualRun(orchestrableRunContext, stageRunId);
        AtomicReference<String> executorIdReference = new AtomicReference<>();
        Map<String, StageRun<?>> currentRunResult = splitProcess(actualRun,
                functionalStageRun -> Map.of(stageRunId, FunctionalStageRunFactory.updateState(functionalStageRun, OrchestrableContextStatus.RUNNING)),
                technicalStageRun -> {
                    executorIdReference.set(technicalStageRun.getExecutorId());
                    return Map.of(stageRunId, TechnicalStageRunFactory.launched(technicalStageRun, instant));
                }
        );

        String executorId = executorIdReference.get();
        if (orchestrableRunContext.getStatus().isProblem() && !actualRun.getStatus().isTerminal()) {
            this.stageRunService.requestCancel(stageRunId, executorId);
        }
        return currentRunResult;
    }
}