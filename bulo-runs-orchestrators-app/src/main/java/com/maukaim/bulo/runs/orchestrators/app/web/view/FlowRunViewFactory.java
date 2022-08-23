package com.maukaim.bulo.runs.orchestrators.app.web.view;

import com.maukaim.bulo.commons.core.TimeHelper;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.models.StageRun;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlowRunViewFactory {

    public static FlowRunView build(FlowRun run) {
        Map<FlowStageId, List<StageRun>> runningStages = run.getInFlightStageRuns().stream()
                .collect(Collectors.groupingBy(StageRun::getFlowStageId));

        Instant startTime = null;
        Instant endTime = null;
        boolean shouldSetEndTime = run.getFlowRunStatus().isTerminal();
        for (StageRun stageRun : run.getAllStageRuns()) {
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

        return new FlowRunView(run.getFlowRunId(),
                run.getFlowId(),
                run.getFlowRunStatus(),
                runningStages,
                startTime,
                endTime);
    }
}
