package com.maukaim.bulo.runs.orchestrator.web.view;

import com.maukaim.bulo.runs.orchestrator.flow.run.FlowRun;
import com.maukaim.bulo.runs.orchestrator.flow.view.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlowRunViewFactory {

    public static FlowRunView build(FlowRun run){
        Map<FlowStageId, List<StageRunView>> runningStages = run.getInFlightStageRuns().stream()
                .collect(Collectors.groupingBy(StageRunView::getFlowStageId));
        return new FlowRunView(run.getFlowRunId(),
                run.getFlowId(),
                run.getFlowRunStatus(),
                runningStages);
    }
}
