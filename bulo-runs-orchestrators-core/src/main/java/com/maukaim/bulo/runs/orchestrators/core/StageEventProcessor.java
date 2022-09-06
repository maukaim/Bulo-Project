package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.commons.io.IStageRunEvent;

public abstract class StageEventProcessor<SE extends IStageRunEvent> {

    protected final FlowRunService flowRunService;

    public StageEventProcessor(FlowRunService flowRunService) {
        this.flowRunService = flowRunService;
    }

    public void castAndProcess(IStageRunEvent event, String flowRunId) {
        SE casted = this.getExpectedStageEventClass().cast(event);
        this.process(casted, flowRunId);
    }

    public abstract Class<SE> getExpectedStageEventClass();

    protected abstract void process(SE event, String flowRunId);
}
