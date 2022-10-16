package com.maukaim.bulo.standalone.data.lifecycle.runs;

import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStoreException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class MainFlowRunStore implements FlowRunStore {
    private final ConcurrentHashMap<String, FlowRun> flowRunById;

    public MainFlowRunStore(Map<String, FlowRun> initialCache) {
        this.flowRunById = new ConcurrentHashMap<>(initialCache);

    }

    @Override
    public FlowRun getRun(String runId) {
        return this.flowRunById.get(runId);
    }

    @Override
    public FlowRun put(FlowRun flowRun) {
        FlowRun newVersion = this.flowRunById.compute(flowRun.getFlowRunId(), (id, previous) -> flowRun);
        return newVersion;
    }

    @Override
    public FlowRun compute(String flowRunId, BiFunction<String, FlowRun, FlowRun> valueComputer) {
        return this.flowRunById.compute(flowRunId, valueComputer);
    }

    @Override
    public FlowRun add(FlowRun flowRun) {
        FlowRun persistedValue = this.flowRunById.compute(flowRun.getFlowRunId(), (id, existingValue) -> {
            if (existingValue != null) {
                throw new FlowRunStoreException("Cache already has a FlowRun with id: " + flowRun.getFlowRunId());
            }
            return flowRun;
        });
        return persistedValue;
    }
}
