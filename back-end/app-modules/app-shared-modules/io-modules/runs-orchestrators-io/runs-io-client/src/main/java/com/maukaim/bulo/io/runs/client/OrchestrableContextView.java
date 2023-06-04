package com.maukaim.bulo.io.runs.client;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;

import java.time.Instant;
import java.util.List;

public abstract class OrchestrableContextView<ID, SOURCE_ID> {
    private final ID runId;
    private final SOURCE_ID sourceId;
    private final OrchestrableContextStatus status;
    private final List<?> stageRuns;
    private final Instant startTime;
    private final Instant endTime;

    public OrchestrableContextView(ID runId, SOURCE_ID sourceId, OrchestrableContextStatus status, List<?> stageRuns, Instant startTime, Instant endTime) {
        this.runId = runId;
        this.sourceId = sourceId;
        this.status = status;
        this.stageRuns = stageRuns;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ID getRunId() {
        return runId;
    }

    public SOURCE_ID getSourceId() {
        return sourceId;
    }

    public OrchestrableContextStatus getStatus() {
        return status;
    }

    public List<?> getStageRuns() {
        return stageRuns;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}
