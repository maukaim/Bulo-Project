package com.maukaim.bulo.runs.orchestrator.stage.run;

import com.maukaim.bulo.runs.orchestrator.flow.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.stage.run.event.StageRunEvent;

public abstract class StageEventProcessor<SE extends StageRunEvent> {

    protected final FlowRunService flowRunService;

    public StageEventProcessor(FlowRunService flowRunService){
        this.flowRunService = flowRunService;
    }

    public void castAndProcess(StageRunEvent event, String flowRunId) {
        SE casted = this.getExpectedStageEventClass().cast(event);
        this.process(casted, flowRunId);
    }

    public abstract Class<SE> getExpectedStageEventClass();

    protected abstract void process(SE event, String flowRunId);
}
