package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;

import java.util.Optional;

public interface FlowStore {
    Optional<Flow> getById(String flowId);
    void put(Flow flow);
    Flow remove(String flowId);
}
