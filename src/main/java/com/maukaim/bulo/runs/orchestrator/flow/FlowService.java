package com.maukaim.bulo.runs.orchestrator.flow;

import com.maukaim.bulo.runs.orchestrator.flow.view.Flow;

import java.util.Optional;

public interface FlowService {
    Optional<Flow> getFlow(String flowId);
}
