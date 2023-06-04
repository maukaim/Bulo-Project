package com.maukaim.bulo.data.lifecycle.runs.client;

import com.maukaim.bulo.commons.utils.TimeHelper;
import com.maukaim.bulo.io.runs.client.FlowRunContextView;
import com.maukaim.bulo.io.runs.client.FunctionalStageRunContextView;
import com.maukaim.bulo.io.runs.client.OrchestrableContextView;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.time.Instant;
import java.util.List;

public class OrchestrableContextViewFactory {

    public static OrchestrableContextView<?, ?> build(OrchestrableRunContext<?> orchestrableRunContext) {
        List<?> runningStages = orchestrableRunContext.getAllStageRuns().stream()
                .map(stageRun -> {
                    if (stageRun instanceof FunctionalStageRun) {
                        return build((FunctionalStageRun) stageRun);
                    } else {
                        return stageRun;
                    }
                })
                .toList();

        Instant startTime = null;
        Instant endTime = null;
        boolean shouldSetEndTime = orchestrableRunContext.getStatus().isTerminal();
        for (StageRun<?> stageRun : orchestrableRunContext.getAllStageRuns()) {
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

        if (orchestrableRunContext instanceof FunctionalStageRun) {
            FunctionalStageRun fStageRun = (FunctionalStageRun) orchestrableRunContext;
            return new FunctionalStageRunContextView(fStageRun.getContextId(),
                    fStageRun.getContextualizedStageId(),
                    fStageRun.getStatus(),
                    runningStages,
                    startTime,
                    endTime);
        } else {
            FlowRun flowRun = (FlowRun) orchestrableRunContext;
            return new FlowRunContextView(flowRun.getContextId(),
                    flowRun.getFlowId(),
                    flowRun.getStatus(),
                    runningStages,
                    startTime,
                    endTime);
        }
    }
}
