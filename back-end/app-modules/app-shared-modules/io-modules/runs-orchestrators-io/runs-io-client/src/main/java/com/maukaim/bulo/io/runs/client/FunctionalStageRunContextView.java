package com.maukaim.bulo.io.runs.client;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class FunctionalStageRunContextView extends OrchestrableContextView<String, ContextStageId> {
    public FunctionalStageRunContextView(String runId, ContextStageId contextStageId, OrchestrableContextStatus status, List<?> stageRuns, Instant startTime, Instant endTime) {
        super(runId, contextStageId, status, stageRuns, startTime, endTime);
    }
}
