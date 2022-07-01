package com.maukaim.boule.flows.orchestrator.publisher;

import com.maukaim.boule.flows.orchestrator.flow.run.FlowRun;

public interface FlowRunUpdatePublisher {
    /**
     * Publish the last version of entity to relevant micro-service.
     * @param flowRun is the new entity state to publish
     */
    void publishUpdate(FlowRun flowRun);
}
