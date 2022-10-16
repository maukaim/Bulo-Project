package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.flow.Flow;

public interface FlowService {
    Flow getFlow(String flowId);
    Flow put(Flow flow);
    Flow archive(String flowId);
}
