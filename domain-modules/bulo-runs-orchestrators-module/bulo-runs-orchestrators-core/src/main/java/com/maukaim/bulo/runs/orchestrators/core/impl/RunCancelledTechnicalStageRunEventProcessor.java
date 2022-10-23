package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.TechnicalStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RunCancelledTechnicalStageRunEventProcessor extends TechnicalStageRunEventProcessor {

    public RunCancelledTechnicalStageRunEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService, stageRunService);
    }

    public void process(String stageRunId, Instant instant, FunctionalStageContext context) {
        System.out.println("Cancelled Run event... " + stageRunId);
        stageRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFunctionalStageRun) -> commonProcess(actualFunctionalStageRun, stageRunId, instant));
    }

    public void process(String stageRunId, Instant instant, FlowContext context) {
        System.out.println("Cancelled Run event... " + stageRunId);
        flowRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFlowRun) -> commonProcess(actualFlowRun, stageRunId, instant));
    }

    private Map<String, StageRun> commonProcess(OrchestrableContext<?> orchestrableContext, String stageRunId, Instant instant) {
        Map<String, StageRun> result = new HashMap<>();
        TechnicalStageRun actualView = getActualTechnicalRun(orchestrableContext, stageRunId);
        result.put(stageRunId, TechnicalStageRunFactory.cancelled(actualView, instant));

        Map<String, StageRun> toBeCancelledStages = cancelOtherStages(stageRunId, orchestrableContext.getInFlightStageRuns());
        result.putAll(toBeCancelledStages);

        return result;
    }

    private Map<String, StageRun> cancelOtherStages(String currentStageRunId, Set<StageRun> inFlightStageRuns) {
        Map<String, StageRun> result = new HashMap<>();
        for (StageRun stageRun : inFlightStageRuns) {
            if (stageRun instanceof TechnicalStageRun) {
                TechnicalStageRun technicalStageRun = (TechnicalStageRun) stageRun;
                String inflightStageRunId = technicalStageRun.getStageRunId();
                if (!currentStageRunId.equals(inflightStageRunId) && technicalStageRun.getStatus() != TechnicalStageRunStatus.TO_BE_CANCELLED) {
                    result.put(inflightStageRunId, TechnicalStageRunFactory.toBeCancelled(technicalStageRun));
                    if (technicalStageRun.getExecutorId() == null) {
                        this.stageRunService.requestCancel(inflightStageRunId);
                    } else {
                        this.stageRunService.requestCancel(inflightStageRunId, technicalStageRun.getExecutorId());
                    }
                }
            } else if (stageRun instanceof FunctionalStageRun) {
                this.stageRunService.requestCancel(stageRun.getStageRunId());

            } else {
                throw new UnsupportedOperationException("Does not support the following StageRun class: " + stageRun.getClass().getName());
            }
        }
        return result;
    }
}
