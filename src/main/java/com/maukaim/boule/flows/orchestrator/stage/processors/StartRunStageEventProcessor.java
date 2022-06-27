package com.maukaim.boule.flows.orchestrator.stage.processors;

import com.maukaim.boule.flows.orchestrator.run.FlowRunService;
import com.maukaim.boule.flows.orchestrator.stage.StageEventProcessor;
import com.maukaim.boule.flows.orchestrator.stage.event.StartRunStageEvent;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunViewFactory;

import java.util.Map;

public class StartRunStageEventProcessor extends StageEventProcessor<StartRunStageEvent> {

    public StartRunStageEventProcessor(FlowRunService flowRunService) {
        super(flowRunService);
    }

    @Override
    public Class<StartRunStageEvent> getExpectedStageEventClass() {
        return StartRunStageEvent.class;
    }

    @Override
    public void process(StartRunStageEvent event) {
        System.out.println("Received StartRunEvent, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(event.getFlowRunId(), (actualFlowRun) -> {
            StageRunView actualView = actualFlowRun.getViewByStageId().get(event.getStageId());
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            StageRunView nextView = StageRunViewFactory.launched(actualView, event.getExecutorId(), event.getStageRunId());
            return Map.of(event.getStageId(), nextView);
        });
    }
}
