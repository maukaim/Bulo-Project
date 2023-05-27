package com.maukaim.bulo.flows.data;

import com.maukaim.bulo.flows.data.models.flow.Flow;

import java.util.List;

public interface FlowStore {
    Flow getById(String flowId);
    Flow put(Flow flow);
    Flow remove(Flow flow);
    List<Flow> getAll();
}
