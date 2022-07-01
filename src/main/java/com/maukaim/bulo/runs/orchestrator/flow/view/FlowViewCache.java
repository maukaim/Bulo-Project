package com.maukaim.bulo.runs.orchestrator.flow.view;

import java.util.Optional;

public interface FlowViewCache {
    Optional<FlowView> getById(String flowId);
    void put(String flowId, FlowView flowView);
}
