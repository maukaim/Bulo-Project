package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FlowRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRunStatus;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RunFailedStageRunEventProcessor extends StageRunEventProcessor {

    public RunFailedStageRunEventProcessor(FlowRunService flowRunService, StageRunService stageRunService, FunctionalStageRunFactory functionalStageRunFactory, TechnicalStageRunFactory technicalStageRunFactory) {
        super(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
    }

    public void process(String stageRunId, Instant instant, FunctionalStageRunContext context) {
        System.out.println("Failed Run event... " + stageRunId);
        stageRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFunctionalStageRun) -> commonProcess(actualFunctionalStageRun, stageRunId, instant));
    }

    public void process(String stageRunId, Instant instant, FlowRunContext context) {
        System.out.println("Failed Run event... " + stageRunId);
        flowRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFlowRun) -> commonProcess(actualFlowRun, stageRunId, instant));
    }

    private Map<String, StageRun<?>> commonProcess(OrchestrableRunContext<?> orchestrableRunContext, String stageRunId, Instant instant) {
        Map<String, StageRun<?>> result = new HashMap<>();
        StageRun<?> actualRun = getActualRun(stageRunId);
        Map<String, StageRun<?>> currentRunResult = splitProcess(actualRun,
                functionalStageRun -> Map.of(stageRunId, functionalStageRunFactory.updateState(functionalStageRun, OrchestrableContextStatus.FAILED)),
                technicalStageRun -> Map.of(stageRunId, technicalStageRunFactory.failed(technicalStageRun, instant)));
        result.putAll(currentRunResult);

        Map<String, StageRun<?>> toBeCancelledStages = cancelOtherStages(stageRunId, orchestrableRunContext.getInFlightStageRuns());
        result.putAll(toBeCancelledStages);

        return result;
    }

    private Map<String, StageRun<?>> cancelOtherStages(String currentStageRunId, Set<StageRun<?>> inFlightStageRuns) {
        Map<String, StageRun<?>> result = new HashMap<>();
        for (StageRun<?> stageRun : inFlightStageRuns) {
            Map<String, StageRun<?>> subResult = splitProcess(stageRun,
                    functionalStageRun -> {
                        this.stageRunService.requestCancel(functionalStageRun.getStageRunId(), null);
                        return Map.of();
                    },
                    technicalStageRun -> {
                        String inflightStageRunId = technicalStageRun.getStageRunId();
                        if (!currentStageRunId.equals(inflightStageRunId) && technicalStageRun.getStatus() != TechnicalStageRunStatus.TO_BE_CANCELLED) {
                            this.stageRunService.requestCancel(inflightStageRunId, technicalStageRun.getExecutorId());
                            return Map.of(inflightStageRunId, technicalStageRunFactory.toBeCancelled(technicalStageRun));
                        }
                        return Map.of();
                    });
            result.putAll(subResult);
        }
        return result;
    }
}
