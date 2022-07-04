package com.maukaim.bulo.runs.orchestrator.flow.view;

import java.util.Optional;

public interface FlowCache {
    Optional<Flow> getById(String flowId);
    void put(String flowId, Flow flow);
}
