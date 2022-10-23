package com.maukaim.bulo.runs.orchestrators.io.models;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.ContextDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public interface StageRunDto<STATUS> {
    StageType getStageType();
    String getStageRunId();
    ContextualizedStageId getContextualizedStageId();
    ContextDto getContext();
    STATUS getStatus();
    Set<StageRunDependencyDto> getDependencies();
    Instant getStartTime();
    Instant getEndTime();

}