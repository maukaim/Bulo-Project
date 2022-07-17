package com.maukaim.bulo.runs.orchestrator.core.flow;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FlowCacheImpl implements FlowCache {
    private final ConcurrentHashMap<String, Flow> cache = new ConcurrentHashMap<>();

    @Override
    public Optional<Flow> getById(String flowId) {
        return Optional.ofNullable(this.cache.get(flowId));
    }

    @Override
    public void put(String flowId, Flow flow) {
        this.cache.put(flowId, flow);
    }
}
