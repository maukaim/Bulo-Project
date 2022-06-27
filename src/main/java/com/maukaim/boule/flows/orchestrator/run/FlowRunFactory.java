package com.maukaim.boule.flows.orchestrator.run;

import com.maukaim.boule.flows.orchestrator.flow.model.Flow;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FlowRunFactory {

    public static FlowRun updateState(FlowRun flowRun, FlowRunStatus newStatus){
        return new FlowRun(flowRun.getFlowRunId(), flowRun.getFlowId(), flowRun.getExecutionGraph(), flowRun.getViewByStageId() , newStatus);
    }

    public static FlowRun updateStageRunView(FlowRun flowRun, Map<String, StageRunView> mapOfViewToBeUpdated){
        HashMap<String, StageRunView> newStageRunViewMap = new HashMap<>(flowRun.getViewByStageId());
        newStageRunViewMap.putAll(mapOfViewToBeUpdated);
        return new FlowRun(flowRun.getFlowRunId(), flowRun.getFlowId(), flowRun.getExecutionGraph(), newStageRunViewMap , flowRun.getFlowRunStatus());
    }

    public static FlowRun newRun(Flow flowToRun){
        return new FlowRun(UUID.randomUUID().toString(),flowToRun.getFlowId(), flowToRun.getExecutionGraph(), new HashMap<>(), FlowRunStatus.NEW);
    }
}
