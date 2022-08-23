package com.maukaim.bulo.runs.orchestrators.core.factories;

import com.maukaim.bulo.runs.orchestrators.data.models.Flow;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.models.StageRun;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FlowRunFactory {

    public static FlowRun updateState(FlowRun flowRun, FlowRunStatus newStatus){
        return new FlowRun(flowRun.getFlowRunId(), flowRun.getFlowId(), flowRun.getExecutionGraph(), flowRun.getStageRunsById() , newStatus);
    }

    public static FlowRun updateStageRunView(FlowRun flowRun, Map<String, StageRun> mapOfViewToBeUpdated){
        HashMap<String, StageRun> newStageRunViewMap = new HashMap<>(flowRun.getStageRunsById());
        newStageRunViewMap.putAll(mapOfViewToBeUpdated);
        return new FlowRun(flowRun.getFlowRunId(), flowRun.getFlowId(), flowRun.getExecutionGraph(), Map.copyOf(newStageRunViewMap) , flowRun.getFlowRunStatus());
    }

    public static FlowRun create(Flow flowToRun){
        return new FlowRun(UUID.randomUUID().toString(),flowToRun.getFlowId(), flowToRun.getExecutionGraph(), new HashMap<>(), FlowRunStatus.NEW);
    }
}
