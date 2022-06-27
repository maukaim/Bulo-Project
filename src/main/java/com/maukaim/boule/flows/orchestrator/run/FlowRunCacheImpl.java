package com.maukaim.boule.flows.orchestrator.run;

import com.maukaim.boule.flows.orchestrator.publisher.FlowRunUpdatePublisher;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;
import com.maukaim.boule.flows.orchestrator.util.CloseableLock;
import com.maukaim.boule.flows.orchestrator.util.EntityLockAcquisitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FlowRunCacheImpl implements FlowRunCache {
    private static final int DEFAULT_LOCK_TIMEOUT_SECONDS = 5;

    private final FlowRunUpdatePublisher flowRunUpdatePublisher;

    private ConcurrentHashMap<String, FlowRun> flowRunById = new ConcurrentHashMap<>();

    @Autowired
    public FlowRunCacheImpl(FlowRunUpdatePublisher flowRunUpdatePublisher) {
        this.flowRunUpdatePublisher = flowRunUpdatePublisher;
    }

    @Override
    public FlowRun getRun(String runId) {
        return this.flowRunById.get(runId);
    }

    @PostConstruct
    public void init() {
        //Insert fake memory
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
    public FlowRun remove(String runId) {
        return this.flowRunById.remove(runId);
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
