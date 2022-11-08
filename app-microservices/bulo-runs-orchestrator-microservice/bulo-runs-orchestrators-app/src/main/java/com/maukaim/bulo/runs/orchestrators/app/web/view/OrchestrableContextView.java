package com.maukaim.bulo.runs.orchestrators.app.web.view;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class OrchestrableContextView<ID> {
    private final ID runId;
    private final String flowId;
    private final OrchestrableContextStatus status;
    private final Map<ContextualizedStageId, List<TechnicalStageRun>> stageRunsByStageId;
    private final Instant startTime;
    private final Instant endTime;

    public OrchestrableContextView(ID runId, String flowId, OrchestrableContextStatus status, Map<ContextualizedStageId, List<TechnicalStageRun>> stageRunsByStageId, Instant startTime, Instant endTime) {
        this.runId = runId;
        this.flowId = flowId;
        this.status = status;
        this.stageRunsByStageId = stageRunsByStageId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ID getRunId() {
        return runId;
    }

    public String getFlowId() {
        return flowId;
    }

    public OrchestrableContextStatus getStatus() {
        return status;
    }

    public Map<ContextualizedStageId, List<TechnicalStageRun>> getStageRunsByStageId() {
        return stageRunsByStageId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}
