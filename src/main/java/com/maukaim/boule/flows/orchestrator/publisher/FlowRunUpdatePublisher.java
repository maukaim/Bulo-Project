package com.maukaim.boule.flows.orchestrator.publisher;

import com.maukaim.boule.flows.orchestrator.run.FlowRun;

import java.util.Set;

public interface FlowRunUpdatePublisher {
    /**
     * Publish the last version of entity to relevant micro-service.
     * @param flowRun is the new entity state to publish
     */
    void publishUpdate(FlowRun flowRun);
}
