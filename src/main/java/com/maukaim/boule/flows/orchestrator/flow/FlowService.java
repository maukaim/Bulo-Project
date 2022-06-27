package com.maukaim.boule.flows.orchestrator.flow;

import com.maukaim.boule.flows.orchestrator.flow.model.Flow;
import com.maukaim.boule.flows.orchestrator.run.FlowRun;
import com.maukaim.boule.triggers.api.TriggerEvent;

import java.util.Optional;

public interface FlowService {
    Optional<Flow> getFlow(String flowId);
}
