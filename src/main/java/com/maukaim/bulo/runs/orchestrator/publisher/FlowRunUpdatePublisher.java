package com.maukaim.bulo.runs.orchestrator.publisher;

import com.maukaim.bulo.runs.orchestrator.flow.run.FlowRun;

public interface FlowRunUpdatePublisher {
    /**
     * Publish the last version of entity to relevant micro-service.
     * @param flowRun is the new entity state to publish
     */
    void publishUpdate(FlowRun flowRun);
}
