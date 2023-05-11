package com.maukaim.bulo.io.runs.orchestrators.system.models;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.RunContextDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public interface StageRunDto<STATUS> {
    StageType getStageType();
    String getStageRunId();
    ContextStageId getContextualizedStageId();
    RunContextDto<?> getContext();
    STATUS getStatus();
    Set<StageRunDependencyDto> getDependencies();
    Instant getStartTime();
    Instant getEndTime();

}