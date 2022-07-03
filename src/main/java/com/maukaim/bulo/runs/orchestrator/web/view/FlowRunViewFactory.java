package com.maukaim.bulo.runs.orchestrator.web.view;

import com.maukaim.bulo.common.utils.TimeHelper;
import com.maukaim.bulo.flows.api.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.flow.run.FlowRun;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlowRunViewFactory {

    public static FlowRunView build(FlowRun run) {
        Map<FlowStageId, List<StageRunView>> runningStages = run.getInFlightStageRuns().stream()
                .collect(Collectors.groupingBy(StageRunView::getFlowStageId));

        Instant startTime = null;
        Instant endTime = null;
        boolean shouldSetEndTime = run.getFlowRunStatus().isTerminal();
        for (StageRunView stageRunView : run.getAllStageRuns()) {
            Instant stageStartTime = stageRunView.getStartTime();
            if (TimeHelper.isBefore(stageStartTime, startTime)) {
                startTime = stageStartTime;
            }

            if (shouldSetEndTime) {
                Instant stageEndTime = stageRunView.getEndTime();
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
