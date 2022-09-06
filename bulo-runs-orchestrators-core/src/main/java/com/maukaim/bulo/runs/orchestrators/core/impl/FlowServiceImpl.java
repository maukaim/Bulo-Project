package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;

import java.util.Optional;

public class FlowServiceImpl implements FlowService {

    private final FlowStore flowStore;

    public FlowServiceImpl(FlowStore flowStore) {
        this.flowStore = flowStore;
    }

    @Override
    public Optional<Flow> getFlow(String flowId) {
        return this.flowStore.getById(flowId);
    }
}
