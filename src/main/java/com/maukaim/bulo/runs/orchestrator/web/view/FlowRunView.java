package com.maukaim.bulo.runs.orchestrator.web.view;

import com.maukaim.bulo.runs.orchestrator.flow.run.FlowRunStatus;
import com.maukaim.bulo.runs.orchestrator.flow.view.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class FlowRunView {
    private final String runId;
    private final String flowId;
    private final FlowRunStatus status;
    private final Map<FlowStageId, List<StageRunView>> stageRunsByStageId;
    private final Instant startTime;
    private final Instant endTime;

    public FlowRunView(String runId, String flowId, FlowRunStatus status, Map<FlowStageId, List<StageRunView>> stageRunsByStageId, Instant startTime, Instant endTime) {
        this.runId = runId;
        this.flowId = flowId;
        this.status = status;
        this.stageRunsByStageId = stageRunsByStageId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getRunId() {
        return runId;
    }

    public String getFlowId() {
        return flowId;
    }

    public FlowRunStatus getStatus() {
        return status;
    }

    public Map<FlowStageId, List<StageRunView>> getStageRunsByStageId() {
        return stageRunsByStageId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}
