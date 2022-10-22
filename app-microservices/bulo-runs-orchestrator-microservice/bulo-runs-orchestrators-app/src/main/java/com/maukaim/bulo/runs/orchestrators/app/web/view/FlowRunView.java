package com.maukaim.bulo.runs.orchestrators.app.web.view;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class FlowRunView {
    private final String runId;
    private final String flowId;
    private final FlowRunStatus status;
    private final Map<ContextualizedStageId, List<StageRun>> stageRunsByStageId;
    private final Instant startTime;
    private final Instant endTime;

    public FlowRunView(String runId, String flowId, FlowRunStatus status, Map<ContextualizedStageId, List<StageRun>> stageRunsByStageId, Instant startTime, Instant endTime) {
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

    public Map<ContextualizedStageId, List<StageRun>> getStageRunsByStageId() {
        return stageRunsByStageId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}
