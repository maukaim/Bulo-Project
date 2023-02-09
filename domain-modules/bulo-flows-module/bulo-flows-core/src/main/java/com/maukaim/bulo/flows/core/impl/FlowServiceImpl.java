package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.core.FlowValidationException;
import com.maukaim.bulo.flows.core.FlowValidator;
import com.maukaim.bulo.flows.data.FlowStore;
import com.maukaim.bulo.flows.data.models.flow.Flow;

import java.util.UUID;

public class FlowServiceImpl implements FlowService {
    private final FlowStore flowStore;
    private final FlowValidator flowValidator;

    public FlowServiceImpl(FlowStore flowStore, FlowValidator flowValidator) {
        this.flowStore = flowStore;
        this.flowValidator = flowValidator;
    }

    @Override
    public Flow getFlow(String flowId) {
        return flowStore.getById(flowId);
    }

    @Override
    public String create(Flow flow) {
        try {
            this.flowValidator.validate(flow);
            Flow flowToStore = attachUUID(flow);
            this.flowStore.put(flowToStore);
            return flowToStore.getFlowId();
        } catch (FlowValidationException e) {
            throw new RuntimeException("Flow validation failed, won't register flow Id " + flow.getFlowId(), e);
        }
    }

    @Override
    public Flow archive(String flowId) {
        Flow flow = this.flowStore.getById(flowId);
        return flow == null ? null : this.flowStore.remove(flow);
    }

    private Flow attachUUID(Flow flow) {
        return new Flow(
                flow.getFlowId() == null ? UUID.randomUUID().toString() : flow.getFlowId(),
                flow.getOwnerKeys(),
                flow.getFlowStages(),
                flow.isParallelRunAllowed()
        );
    }
}