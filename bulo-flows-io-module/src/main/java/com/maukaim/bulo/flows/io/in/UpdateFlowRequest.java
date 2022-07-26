package com.maukaim.bulo.flows.io.in;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.flows.io.FlowData;

import java.time.Instant;

public class UpdateFlowRequest{
    private FlowData flowData;

    public UpdateFlowRequest(FlowData flowData) {
        this.flowData = flowData;
    }

    public FlowData getFlowData() {
        return flowData;
    }

    @Override
    public String toString() {
        return "updateFlowRequest{" +
                "flowData=" + flowData +
                '}';
    }
}
