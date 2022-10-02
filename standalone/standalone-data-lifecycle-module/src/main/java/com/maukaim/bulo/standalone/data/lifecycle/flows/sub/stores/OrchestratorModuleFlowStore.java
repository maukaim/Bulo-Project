package com.maukaim.bulo.standalone.data.lifecycle.flows.sub.stores;

import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.standalone.data.lifecycle.UnsupportedDataMethodException;
import com.maukaim.bulo.standalone.data.lifecycle.flows.MainFlowStore;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.FlowAdapter;

import java.util.Optional;

public class OrchestratorModuleFlowStore implements FlowStore {
    private final MainFlowStore mainFlowStore;
    private final FlowAdapter flowAdapter;

    public OrchestratorModuleFlowStore(MainFlowStore mainFlowStore, FlowAdapter flowAdapter) {
        this.mainFlowStore = mainFlowStore;
        this.flowAdapter = flowAdapter;
    }

    @Override
    public Optional<Flow> getById(String flowId) {
        com.maukaim.bulo.flows.data.models.flow.Flow flow = this.mainFlowStore.getById(flowId);
        return Optional.ofNullable(this.flowAdapter.adapte(flow));
    }

    @Override
    public void put(Flow flow) {
        throw UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public Flow remove(String flowId) {
        throw UnsupportedDataMethodException.isSubStore();
    }
}
