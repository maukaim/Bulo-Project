package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.commons.models.StageType;

import java.time.Instant;
import java.util.Set;

public interface StageRun<STATUS extends RunStatus> {

    StageType getStageType();

    String getStageRunId();

    ContextualizedStageId getContextualizedStageId();


    RunContext<?> getContext();

    STATUS getStatus();

    Set<RunDependency> getStageRunDependencies();

    default boolean isTerminated(){
        return getStatus() != null && this.getStatus().isTerminal();
    }

    Instant getEndTime();

    Instant getStartTime();
}
