package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.flow.Flow;

import java.util.List;

public interface FlowService {
    Flow getFlow(String flowId);
    List<Flow> getAll();
    String create(Flow flow);
    Flow archive(String flowId);
}
