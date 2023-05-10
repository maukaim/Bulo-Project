package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.FlowRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStoreException;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowRunDto;

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
    public synchronized FlowRun compute(String flowRunId, BiFunction<String, FlowRun, FlowRun> valueComputer){
        FlowRun persistedFlowRun = this.flowRunById.compute(flowRunId, valueComputer);
        FlowRunDto dto = this.flowRunDtoAdapter.adapte(persistedFlowRun);
        FlowRunEvent flowRunEvent = new FlowRunEvent(dto, Instant.now());
        flowRunEventPublisher.publish(flowRunEvent);
        return persistedFlowRun;
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
        System.out.println((flowRun.getStatus().isTerminal()? ">>> Save TERMINAL flowRun -> " : "Save intermediate flowRun -> ") + flowRun);
        //Only save if it is one NOT managed by this instance
        return this.flowRunById.put(flowRun.getContextId(), flowRun);
    }
}
