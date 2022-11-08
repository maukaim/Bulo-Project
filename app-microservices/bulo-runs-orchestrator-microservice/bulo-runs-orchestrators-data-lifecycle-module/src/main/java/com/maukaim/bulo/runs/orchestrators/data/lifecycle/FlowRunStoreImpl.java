package com.maukaim.bulo.runs.orchestrators.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStoreException;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.io.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.events.FlowRunEvent;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunDto;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class FlowRunStoreImpl implements FlowRunStore {
    private final FlowRunEventPublisher flowRunEventPublisher;
    private final FlowRunDtoAdapter flowRunDtoAdapter;
    private final ConcurrentHashMap<String, FlowRun> flowRunById = new ConcurrentHashMap<>();

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
        FlowRun newVersion = this.flowRunById.compute(flowRun.getContextId(), (id, previous) -> flowRun);
        FlowRunDto dto = this.flowRunDtoAdapter.adapte(newVersion);
        FlowRunEvent flowRunEvent = new FlowRunEvent(dto, Instant.now());
        flowRunEventPublisher.publish(flowRunEvent);
        return newVersion;
    }

    @Override
    public synchronized FlowRun compute(String flowRunId, BiFunction<String, FlowRun, FlowRun> valueComputer){
        return this.flowRunById.compute(flowRunId, valueComputer);
    }

    @Override
    public FlowRun add(FlowRun flowRun) {
        FlowRun persistedValue = this.flowRunById.compute(flowRun.getContextId(), (id, existingValue) -> {
            if (existingValue != null) {
                throw new FlowRunStoreException("Cache already has a FlowRun with id: " + flowRun.getContextId());
            }
            return flowRun;
        });
        FlowRunDto dto = this.flowRunDtoAdapter.adapte(persistedValue);
        FlowRunEvent flowRunEvent = new FlowRunEvent(dto, Instant.now());
        flowRunEventPublisher.publish(flowRunEvent);
        return persistedValue;
    }

    public FlowRun save(FlowRun flowRun) {
        System.out.println("Will save flowRun -> " + flowRun);
        return this.flowRunById.put(flowRun.getContextId(), flowRun);
    }
}
