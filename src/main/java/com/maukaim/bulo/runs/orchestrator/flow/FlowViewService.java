package com.maukaim.bulo.runs.orchestrator.flow;

import com.maukaim.bulo.runs.orchestrator.flow.view.FlowView;

import java.util.Optional;

public interface FlowViewService {
    Optional<FlowView> getFlow(String flowId);
}
