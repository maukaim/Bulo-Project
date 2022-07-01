package com.maukaim.boule.flows.orchestrator.flow.run;

import com.maukaim.boule.flows.orchestrator.publisher.FlowRunUpdatePublisher;
import com.maukaim.boule.flows.orchestrator.util.CloseableLock;
import com.maukaim.boule.flows.orchestrator.util.EntityLockAcquisitionException;

import java.util.concurrent.ConcurrentHashMap;

public class FlowRunCacheImpl implements FlowRunCache {
    private final FlowRunUpdatePublisher flowRunUpdatePublisher;
    private final ConcurrentHashMap<String, FlowRun> flowRunById = new ConcurrentHashMap<>();

    public FlowRunCacheImpl(FlowRunUpdatePublisher flowRunUpdatePublisher) {
        this.flowRunUpdatePublisher = flowRunUpdatePublisher;
    }

    @Override
    public FlowRun getRun(String runId) {
        return this.flowRunById.get(runId);
    }

    @Override
    public FlowRun update(FlowRun flowRun) {
        //TODO: Add concept of versions ? What Pros? What Cons?
        FlowRun newVersion = this.flowRunById.compute(flowRun.getFlowRunId(), (id, previous) -> flowRun);
        flowRunUpdatePublisher.publishUpdate(newVersion);
        return newVersion;
    }

    @Override
    public FlowRun add(FlowRun flowRun) {
        FlowRun persistedValue = this.flowRunById.compute(flowRun.getFlowRunId(), (id, existingValue) -> {
            if (existingValue != null) {
                throw new FlowRunCacheException("Cache already has a FlowRun with id: " + flowRun.getFlowRunId());
            }
            return flowRun;
        });
        flowRunUpdatePublisher.publishUpdate(persistedValue);
        return persistedValue;
    }

    @Override
    public CloseableLock<FlowRun> getAndLock(String runId) {
        FlowRun flowRun = this.flowRunById.get(runId);
        if (flowRun == null) {
            throw new EntityLockAcquisitionException("Locking impossible, No FlowRun in cache with Id: " + runId);
        }
        return CloseableLock.of(flowRun);
    }
}
