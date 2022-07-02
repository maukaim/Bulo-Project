package com.maukaim.bulo.runs.orchestrator.stage.run.processors;

import com.maukaim.bulo.runs.orchestrator.flow.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.stage.run.event.RunCancelledStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunViewFactory;

import java.util.Map;

public class RunCancelledStageEventProcessor extends StageEventProcessor<RunCancelledStageRunEvent> {
    public RunCancelledStageEventProcessor(FlowRunService flowRunService) {
        super(flowRunService);
    }

    @Override
    public Class<RunCancelledStageRunEvent> getExpectedStageEventClass() {
        return RunCancelledStageRunEvent.class;
    }

    @Override
    public void process(RunCancelledStageRunEvent event, String flowRunId) {
        System.out.println("Received CANCELLED run Event, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            StageRunView actualView = actualFlowRun.getStageRunsById().get(event.getStageRunId());
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            StageRunView computedView = StageRunViewFactory.cancelled(actualView, event.getInstant());
            return Map.of(event.getStageRunId(), computedView);
        });
    }
}
