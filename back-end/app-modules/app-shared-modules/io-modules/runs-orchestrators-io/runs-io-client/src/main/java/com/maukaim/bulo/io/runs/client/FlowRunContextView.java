package com.maukaim.bulo.io.runs.client;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;

import java.time.Instant;
import java.util.List;

public class FlowRunContextView extends OrchestrableContextView<String, String> {
    public FlowRunContextView(String runId, String flowId, OrchestrableContextStatus status, List<?> stageRuns, Instant startTime, Instant endTime) {
        super(runId, flowId, status, stageRuns, startTime, endTime);
    }
}
