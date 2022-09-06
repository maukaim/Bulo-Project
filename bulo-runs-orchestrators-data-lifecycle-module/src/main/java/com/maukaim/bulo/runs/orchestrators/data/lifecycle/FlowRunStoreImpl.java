package com.maukaim.bulo.runs.orchestrators.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.CloseableEntityLock;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.io.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.events.FlowRunEvent;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunDto;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FlowRunStoreImpl implements FlowRunStore {
    private final FlowRunEventPublisher flowRunEventPublisher;
    private final FlowRunDtoAdapter flowRunDtoAdapter;
    private final ConcurrentHashMap<String, FlowRun> flowRunById = new ConcurrentHashMap<>();
    private final Map<String, ReentrantLock> lockByEntityId = new ConcurrentHashMap<>();

    public FlowRunStoreImpl(FlowRunEventPublisher flowRunEventPublisher,
                            FlowRunDtoAdapter flowRunDtoAdapter) {
        this.flowRunDtoAdapter = flowRunDtoAdapter;
        this.flowRunEventPublisher = flowRunEventPublisher;
    }

    @Override
    public FlowRun getRun(String runId) {
        return this.flowRunById.get(runId);
    }

    @Override
    public FlowRun put(FlowRun flowRun) {
        FlowRun newVersion = this.flowRunById.compute(flowRun.getFlowRunId(), (id, previous) -> flowRun);
        FlowRunDto dto = this.flowRunDtoAdapter.adapte(newVersion);
        FlowRunEvent flowRunEvent = new FlowRunEvent(dto, Instant.now());
        flowRunEventPublisher.publish(flowRunEvent);
        return newVersion;
    }

    @Override
    public FlowRun add(FlowRun flowRun) {
        FlowRun persistedValue = this.flowRunById.compute(flowRun.getFlowRunId(), (id, existingValue) -> {
            if (existingValue != null) {
                throw new FlowRunStoreException("Cache already has a FlowRun with id: " + flowRun.getFlowRunId());
            }
            return flowRun;
        });
        FlowRunDto dto = this.flowRunDtoAdapter.adapte(persistedValue);
        FlowRunEvent flowRunEvent = new FlowRunEvent(dto, Instant.now());
        flowRunEventPublisher.publish(flowRunEvent);
        return persistedValue;
    }

    public FlowRun save(FlowRun flowRun){
        System.out.println("Will save flowRun -> " + flowRun);
        return this.flowRunById.put(flowRun.getFlowRunId(), flowRun);
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
