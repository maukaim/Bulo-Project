package com.maukaim.bulo.runs.orchestrator.app.web.view;

import com.maukaim.bulo.commons.core.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.model.FlowRunStatus;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRun;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class FlowRunView {
    private final String runId;
    private final String flowId;
    private final FlowRunStatus status;
    private final Map<FlowStageId, List<StageRun>> stageRunsByStageId;
    private final Instant startTime;
    private final Instant endTime;

    public FlowRunView(String runId, String flowId, FlowRunStatus status, Map<FlowStageId, List<StageRun>> stageRunsByStageId, Instant startTime, Instant endTime) {
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

    public Map<FlowStageId, List<StageRun>> getStageRunsByStageId() {
        return stageRunsByStageId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}
