package com.maukaim.bulo.flows.io.in;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.flows.io.FlowData;

import java.time.Instant;

public class RemoveFlowRequest {
    private String flowId;

    public RemoveFlowRequest(Instant instant, String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId() {
        return flowId;
    }
}
