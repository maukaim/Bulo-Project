package com.maukaim.bulo.runs.orchestrator.core.stagerun.processors;

import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.io.in.RunCancelledStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRun;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRunFactory;

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
            StageRun actualView = actualFlowRun.getStageRunsById().get(event.getStageRunId());
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            StageRun computedView = StageRunFactory.cancelled(actualView, event.getInstant());
            return Map.of(event.getStageRunId(), computedView);
        });
    }
}
