package com.maukaim.boule.flows.orchestrator.flow.run;

import com.maukaim.boule.flows.orchestrator.publisher.FlowRunUpdatePublisher;
import com.maukaim.boule.flows.orchestrator.util.CloseableEntityLock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FlowRunCacheImpl implements FlowRunCache {
    private final FlowRunUpdatePublisher flowRunUpdatePublisher;
    private final ConcurrentHashMap<String, FlowRun> flowRunById = new ConcurrentHashMap<>();
    private final Map<String, ReentrantLock> lockByEntityId = new ConcurrentHashMap<>();

    public FlowRunCacheImpl(FlowRunUpdatePublisher flowRunUpdatePublisher) {
        this.flowRunUpdatePublisher = flowRunUpdatePublisher;
    }

    @Override
    public FlowRun getRun(String runId) {
        return this.flowRunById.get(runId);
    }

    @Override
    public FlowRun update(FlowRun flowRun) {
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
    public CloseableEntityLock<FlowRun> getAndLock(String runId) {
        this.lockByEntityId.compute(runId, LockHandler::lock);
        return CloseableEntityLock.of(this.getRun(runId), () -> this.lockByEntityId.compute(runId, LockHandler::unLock));
    }


    private static class LockHandler {
        private static ReentrantLock lock(String id, ReentrantLock lock) {
            if (lock == null) {
                lock = new ReentrantLock();
            }
            lock.lock();
            return lock;
        }

        private static <L extends Lock> L unLock(String id, L lock) {
            if (lock != null) {
                try {
                    lock.unlock();
                } catch (IllegalMonitorStateException ignored) {
                    return lock;
                }
            }
            return null;
        }
    }
}
