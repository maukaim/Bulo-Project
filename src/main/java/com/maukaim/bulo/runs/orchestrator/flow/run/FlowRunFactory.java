package com.maukaim.bulo.runs.orchestrator.flow.run;

import com.maukaim.bulo.runs.orchestrator.flow.view.Flow;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FlowRunFactory {

    public static FlowRun updateState(FlowRun flowRun, FlowRunStatus newStatus){
        return new FlowRun(flowRun.getFlowRunId(), flowRun.getFlowId(), flowRun.getExecutionGraph(), flowRun.getStageRunsById() , newStatus);
    }

    public static FlowRun updateStageRunView(FlowRun flowRun, Map<String, StageRunView> mapOfViewToBeUpdated){
        HashMap<String, StageRunView> newStageRunViewMap = new HashMap<>(flowRun.getStageRunsById());
        newStageRunViewMap.putAll(mapOfViewToBeUpdated);
        return new FlowRun(flowRun.getFlowRunId(), flowRun.getFlowId(), flowRun.getExecutionGraph(), Map.copyOf(newStageRunViewMap) , flowRun.getFlowRunStatus());
    }

    public static FlowRun create(Flow flowToRun){
        return new FlowRun(UUID.randomUUID().toString(),flowToRun.getFlowId(), flowToRun.getExecutionGraph(), new HashMap<>(), FlowRunStatus.NEW);
    }
}
