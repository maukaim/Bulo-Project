package com.maukaim.bulo.runs.orchestrator.flow.view;

import com.maukaim.bulo.runs.orchestrator.flow.FlowService;

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
