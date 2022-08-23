package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.runs.orchestrators.data.models.Flow;

import java.util.Optional;

public interface FlowStore {
    Optional<Flow> getById(String flowId);
    void put(String flowId, Flow flow);
}
