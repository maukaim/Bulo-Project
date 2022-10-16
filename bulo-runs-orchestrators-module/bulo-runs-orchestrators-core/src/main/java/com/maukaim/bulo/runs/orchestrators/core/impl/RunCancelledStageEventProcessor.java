package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.core.factories.StageRunFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RunCancelledStageEventProcessor extends StageEventProcessor {
    private final StageRunService stageRunService;
    public RunCancelledStageEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService);
        this.stageRunService = stageRunService;
    }

    public void process(String stageRunId, Instant instant, String flowRunId) {
        System.out.println("Cancelled Run event... " + stageRunId);

        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            Map<String, StageRun> result = new HashMap<>();
            StageRun actualView = actualFlowRun.getStageRunsById().get(stageRunId);
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            StageRun computedView = StageRunFactory.cancelled(actualView, instant);

            for (StageRun stageRun : actualFlowRun.getInFlightStageRuns()) {
                String inflightStageRunId = stageRun.getStageRunId();
                if (!stageRunId.equals(inflightStageRunId) && stageRun.getStageRunStatus() != StageRunStatus.TO_BE_CANCELLED) {
                    result.put(inflightStageRunId, StageRunFactory.toBeCancelled(stageRun));
                    if (stageRun.getExecutorId() == null) {
                        this.stageRunService.requestCancel(inflightStageRunId);
                    } else {
                        this.stageRunService.requestCancel(inflightStageRunId, stageRun.getExecutorId());
                    }
                }
            }

            result.put(stageRunId, computedView);
            return result;
        });
    }
}
