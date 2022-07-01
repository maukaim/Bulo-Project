package com.maukaim.boule.flows.orchestrator.web.view;

import com.maukaim.boule.flows.orchestrator.flow.run.FlowRun;
import com.maukaim.boule.flows.orchestrator.flow.view.FlowStageId;
import com.maukaim.boule.flows.orchestrator.stage.run.model.StageRunView;

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
