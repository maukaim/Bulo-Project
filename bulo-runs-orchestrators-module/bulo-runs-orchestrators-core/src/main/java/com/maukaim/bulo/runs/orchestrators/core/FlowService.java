package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;

import java.util.Optional;

public interface FlowService {
    Optional<Flow> getFlow(String flowId);
}
