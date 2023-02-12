package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.flow.Flow;

public interface FlowService {
    Flow getFlow(String flowId);
    String create(Flow flow);
    Flow archive(String flowId);
}
