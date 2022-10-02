package com.maukaim.bulo.standalone.data.lifecycle.flows;

import com.maukaim.bulo.flows.data.FlowStore;
import com.maukaim.bulo.flows.data.models.flow.Flow;

import java.util.HashMap;
import java.util.Map;

public class MainFlowStore implements FlowStore {
    private Map<String, Flow> flowById;

    public MainFlowStore(Map<String, Flow> initialCache) {
        this.flowById = new HashMap<>(initialCache);
    }

    @Override
    public Flow getById(String flowId) {
        return this.flowById.get(flowId);
    }

    @Override
    public Flow put(String flowId, Flow flow) {
        System.out.println("Saving flow: " + flow);
        this.flowById.put(flow.getFlowId(), flow);
        return flow;
    }

    @Override
    public Flow remove(Flow flow) {
        System.out.println("Removing flow: " + flow.getFlowId());
        return this.flowById.remove(flow.getFlowId());
    }
}
