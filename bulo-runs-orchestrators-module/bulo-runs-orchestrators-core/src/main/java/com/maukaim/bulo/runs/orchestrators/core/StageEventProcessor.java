package com.maukaim.bulo.runs.orchestrators.core;

public abstract class StageEventProcessor {
    protected final FlowRunService flowRunService;

    public StageEventProcessor(FlowRunService flowRunService) {
        this.flowRunService = flowRunService;
    }
}
