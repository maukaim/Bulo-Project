package com.maukaim.bulo.runs.orchestrators.app.web.view;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.core.utils.TimeHelper;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrchestrableContextViewFactory {

    public static OrchestrableContextView build(OrchestrableContext<?> orchestrableContext) {
        Map<ContextualizedStageId, List<StageRun>> runningStages = orchestrableContext.getInFlightStageRuns().stream()
                .collect(Collectors.groupingBy( stageRun -> stageRun.getContextualizedStageId()));

        Instant startTime = null;
        Instant endTime = null;
        boolean shouldSetEndTime = orchestrableContext.getStatus().isTerminal();
        for (StageRun stageRun : orchestrableContext.getAllStageRuns()) {
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

        return new OrchestrableContextView(orchestrableContext.getContextId(),
                resolveFlowId(orchestrableContext),
                orchestrableContext.getStatus(),
                runningStages,
                startTime,
                endTime);
    }

    private static String resolveFlowId(OrchestrableContext<?> orchestrableContext) {
        if(orchestrableContext instanceof FlowRun) {
            return ((FlowRun) orchestrableContext).getFlowId();
        }
        return null;
    }
}
