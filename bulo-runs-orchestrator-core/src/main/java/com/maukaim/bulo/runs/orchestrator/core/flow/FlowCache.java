package com.maukaim.bulo.runs.orchestrator.core.flow;

import java.util.Optional;

public interface FlowCache {
    Optional<Flow> getById(String flowId);
    void put(String flowId, Flow flow);
}
