package com.maukaim.boule.flows.orchestrator.flow;

import com.maukaim.boule.flows.orchestrator.flow.view.FlowView;

import java.util.Optional;

public interface FlowViewService {
    Optional<FlowView> getFlow(String flowId);
}
