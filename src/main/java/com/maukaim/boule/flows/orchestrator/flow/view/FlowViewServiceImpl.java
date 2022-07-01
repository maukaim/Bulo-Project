package com.maukaim.boule.flows.orchestrator.flow.view;

import com.maukaim.boule.flows.orchestrator.flow.FlowViewService;

import java.util.Optional;

public class FlowViewServiceImpl implements FlowViewService {

    private final FlowViewCache flowViewCache;

    public FlowViewServiceImpl(FlowViewCache flowViewCache) {
        this.flowViewCache = flowViewCache;
    }

    @Override
    public Optional<FlowView> getFlow(String flowId) {
        return this.flowViewCache.getById(flowId);
    }
}
