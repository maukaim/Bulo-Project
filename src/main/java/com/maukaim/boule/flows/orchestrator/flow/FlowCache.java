package com.maukaim.boule.flows.orchestrator.flow;

import com.maukaim.boule.flows.orchestrator.flow.model.Flow;

import java.util.Collection;
import java.util.Optional;

public interface FlowCache {
    Collection<Flow> getAll();
    Optional<Flow> getById(String flowId);
}
