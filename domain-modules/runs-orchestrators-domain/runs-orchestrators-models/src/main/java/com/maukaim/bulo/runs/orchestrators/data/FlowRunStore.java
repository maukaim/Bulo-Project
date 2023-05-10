package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;

import java.util.function.BiFunction;

public interface FlowRunStore extends ContextStore<FlowRun, String>{
    FlowRun getRun(String flowRunId);

    FlowRun add(FlowRun flowRun);
}
