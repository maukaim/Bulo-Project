package com.maukaim.bulo.flows.data;

import com.maukaim.bulo.flows.data.models.flow.Flow;

public interface FlowStore {
    Flow getById(String flowId);
    Flow put(String flowId, Flow flow);
    Flow remove(Flow flow);
}
