package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.io.FlowData;

import java.util.UUID;

public class FlowBuilderImpl {

    public static Flow build(FlowData flowData) {
        return build(
                flowData.getFlowId() != null ? flowData.getFlowId() : UUID.randomUUID().toString(),
                flowData);
    }

    private static Flow build(String flowId, FlowData flowData){

        return new Flow(flowId, flowData.getFlowOwners(), flowData.getAncestorsByStageId(),
                flowData.isParallelRunAllowed());
    }
}
