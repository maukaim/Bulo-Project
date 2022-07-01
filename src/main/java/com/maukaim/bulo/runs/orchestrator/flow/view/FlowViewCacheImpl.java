package com.maukaim.bulo.runs.orchestrator.flow.view;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

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
