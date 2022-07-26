package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.io.FlowData;

public interface FlowService {
    Flow getFlow(String flowId);
    Flow create(FlowData flowData);
    Flow update(FlowData flowData);
    Flow archive(String flowId);
}
