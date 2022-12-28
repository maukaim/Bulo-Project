package com.maukaim.bulo.runs.orchestrators.app.web.view;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.core.utils.TimeHelper;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrchestrableContextViewFactory {

    public static OrchestrableContextView build(OrchestrableRunContext<?> orchestrableRunContext) {
        Map<ContextStageId, List<StageRun>> runningStages = orchestrableRunContext.getInFlightStageRuns().stream()
                .collect(Collectors.groupingBy( stageRun -> stageRun.getContextualizedStageId()));

        Instant startTime = null;
        Instant endTime = null;
        boolean shouldSetEndTime = orchestrableRunContext.getStatus().isTerminal();
        for (StageRun stageRun : orchestrableRunContext.getAllStageRuns()) {
            Instant stageStartTime = stageRun.getStartTime();
            if (TimeHelper.isBefore(stageStartTime, startTime)) {
                startTime = stageStartTime;
            }

            if (shouldSetEndTime) {
                Instant stageEndTime = stageRun.getEndTime();
                if (TimeHelper.isAfter(stageEndTime, endTime)) {
                    endTime = stageEndTime;
                }
            }
        }

        return new OrchestrableContextView(orchestrableRunContext.getContextId(),
                resolveFlowId(orchestrableRunContext),
                orchestrableRunContext.getStatus(),
                runningStages,
                startTime,
                endTime);
    }

    private static String resolveFlowId(OrchestrableRunContext<?> orchestrableRunContext) {
        if(orchestrableRunContext instanceof FlowRun) {
            return ((FlowRun) orchestrableRunContext).getFlowId();
        }
        return null;
    }
}
