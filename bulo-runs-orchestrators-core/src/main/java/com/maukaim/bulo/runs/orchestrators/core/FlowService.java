package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.data.models.Flow;

import java.util.Optional;

public interface FlowService {
    Optional<Flow> getFlow(String flowId);
}
