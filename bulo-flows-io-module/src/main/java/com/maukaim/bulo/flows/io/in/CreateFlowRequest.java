package com.maukaim.bulo.flows.io.in;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.flows.io.FlowData;

import java.time.Instant;

public class CreateFlowRequest {
    private FlowData flowData;

    public CreateFlowRequest( FlowData flowData) {
        this.flowData = flowData;
    }

    public FlowData getFlowData() {
        return flowData;
    }

    @Override
    public String toString() {
        return "CreateFlowRequest{" +
                "flowData=" + flowData +
                '}';
    }
}
