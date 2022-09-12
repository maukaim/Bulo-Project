package com.maukaim.bulo.runs.orchestrators.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FlowStoreImpl implements FlowStore {
    private final ConcurrentHashMap<String, Flow> cache = new ConcurrentHashMap<>();

    @Override
    public Optional<Flow> getById(String flowId) {
        return Optional.ofNullable(this.cache.get(flowId));
    }

    @Override
    public void put(Flow flow) {
        this.cache.put(flow.getFlowId(), flow);
    }

    @Override
    public Flow remove(String flowId) {
        return this.cache.remove(flowId);
    }
}
