package com.maukaim.boule.flows.orchestrator.stage;

import com.maukaim.boule.flows.orchestrator.run.FlowRunService;

public abstract class StageEventProcessor<SE extends StageEvent> {

    protected final FlowRunService flowRunService;

    public StageEventProcessor(FlowRunService flowRunService){
        this.flowRunService = flowRunService;
    }

    public void castAndProcess(StageEvent event) {
        SE casted = this.getExpectedStageEventClass().cast(event);
        this.process(casted);
    }

    public abstract Class<SE> getExpectedStageEventClass();

    protected abstract void process(SE event);
}
