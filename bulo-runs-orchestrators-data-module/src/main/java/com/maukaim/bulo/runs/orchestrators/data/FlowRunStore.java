package com.maukaim.bulo.runs.orchestrators.data;


import com.maukaim.bulo.runs.orchestrators.data.models.CloseableEntityLock;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRun;

public interface FlowRunStore {
    FlowRun getRun(String flowRunId);

    FlowRun add(FlowRun flowRun);

    FlowRun put(FlowRun flowRun);

    CloseableEntityLock<FlowRun> getAndLock(String runId);
}
