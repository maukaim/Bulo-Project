package com.maukaim.bulo.runs.orchestrators.data;


import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;

import java.util.function.BiFunction;

public interface FlowRunStore {
    FlowRun getRun(String flowRunId);

    FlowRun compute(String flowRunId, BiFunction<String, FlowRun, FlowRun> valueComputer);

    FlowRun add(FlowRun flowRun);

    FlowRun put(FlowRun flowRun);

}
