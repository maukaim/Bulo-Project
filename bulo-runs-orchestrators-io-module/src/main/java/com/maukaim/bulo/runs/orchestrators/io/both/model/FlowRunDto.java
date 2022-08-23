package com.maukaim.bulo.runs.orchestrators.io.both.model;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Map;
import java.util.Set;

public class FlowRunDto {
    private final String flowRunId;
    private final String flowId;
    private final Map<FlowStageId, Set<FlowStageId>> stageToAncestors;
    private final Map<String, StageRunDto> stageRunViewByIds;
    private final FlowRunStatusDto flowRunStatus;

    public FlowRunDto(String flowRunId, String flowId, Map<FlowStageId, Set<FlowStageId>> stageToAncestors, Map<String, StageRunDto> stageRunViewByIds, FlowRunStatusDto flowRunStatus) {
        this.flowRunId = flowRunId;
        this.flowId = flowId;
        this.stageToAncestors = stageToAncestors;
        this.stageRunViewByIds = stageRunViewByIds;
        this.flowRunStatus = flowRunStatus;
    }

    public String getFlowRunId() {
        return flowRunId;
    }

    public String getFlowId() {
        return flowId;
    }

    public Map<FlowStageId, Set<FlowStageId>> getStageToAncestors() {
        return stageToAncestors;
    }

    public Map<String, StageRunDto> getStageRunByIds() {
        return stageRunViewByIds;
    }

    public FlowRunStatusDto getFlowRunStatus() {
        return flowRunStatus;
    }
}
