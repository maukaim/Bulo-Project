package com.maukaim.bulo.runs.orchestrator.core;

import com.maukaim.bulo.runs.orchestrator.core.flowrun.model.FlowRun;

public interface FlowRunEventPublisher {
    /**
     * Publish the last version of entity to relevant microservice.
     * @param flowRun is the new entity state to publish
     */
    void publishUpdate(FlowRun flowRun);
}
