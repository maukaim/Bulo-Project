package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface StageRunService extends OrchestrableContextService<FunctionalStageRun, String> {
    Map<String, StageRun<?>> getNextStageRuns(RunContext<?> runContext, Map<ContextStageId, Set<RunDependency>> flowStageToRunByDependencies);

    Map<String, StageRun<?>> startRuns(Collection<StageRun<?>> stageRunToBeRequested);

    StageRun<?> getById(String stageRunId);

    void put(String stageRunId, StageRun<?> stageRun);

    void requestCancel(String stageRunId, String executorId);
}
