package com.maukaim.bulo.runs.orchestrator.core.flow;

import java.util.Optional;

public interface FlowService {
    Optional<Flow> getFlow(String flowId);
}
