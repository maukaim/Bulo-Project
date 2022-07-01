package com.maukaim.boule.flows.orchestrator.flow.view;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.maukaim.boule.flows.orchestrator.config.FakeContextProvider.FLOW_1;

public class FlowViewCacheImpl implements FlowViewCache {
    private final ConcurrentHashMap<String, FlowView> cache = new ConcurrentHashMap<>();

    @Override
    public Optional<FlowView> getById(String flowId) {
        return Optional.ofNullable(this.cache.get(flowId));
    }

    @Override
    public void put(String flowId, FlowView flowView) {
        this.cache.put(flowId, flowView);
    }
}
