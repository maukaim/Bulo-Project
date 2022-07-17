package com.maukaim.bulo.runs.orchestrator.core.flow;

import java.util.Optional;

public class FlowServiceImpl implements FlowService {

    private final FlowCache flowCache;

    public FlowServiceImpl(FlowCache flowCache) {
        this.flowCache = flowCache;
    }

    @Override
    public Optional<Flow> getFlow(String flowId) {
        return this.flowCache.getById(flowId);
    }
}
