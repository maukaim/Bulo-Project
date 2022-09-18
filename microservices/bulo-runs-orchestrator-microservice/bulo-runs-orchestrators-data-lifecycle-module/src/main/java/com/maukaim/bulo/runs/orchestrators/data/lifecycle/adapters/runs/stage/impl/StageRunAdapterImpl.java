package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunDependencyAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class StageRunAdapterImpl implements StageRunAdapter {
    private final StageRunDependencyAdapter stageRunDependencyAdapter;

    public StageRunAdapterImpl(StageRunDependencyAdapter stageRunDependencyAdapter) {
        this.stageRunDependencyAdapter = stageRunDependencyAdapter;
    }

    @Override
    public StageRun adapte(StageRunDto dto) {
        return new StageRun(
                dto.getStageRunId(),
                dto.getFlowStageId(),
                dto.getFlowRunId(),
                resolve(dto.getStageRunStatus()),
                dto.getExecutorId(),
                resolve(dto.getDependencies()),
                dto.getStartTime(),
                dto.getEndTime()
        );
    }

    private Set<StageRunDependency> resolve(Set<StageRunDependencyDto> dependencies) {
        return dependencies == null ? null : dependencies.stream()
                .map(this.stageRunDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private StageRunStatus resolve(StageRunStatusDto stageRunStatusDto) {
        return switch (stageRunStatusDto){
            case TO_BE_REQUESTED -> StageRunStatus.TO_BE_REQUESTED;
            case REQUESTED -> StageRunStatus.REQUESTED;
            case ACKNOWLEDGED -> StageRunStatus.ACKNOWLEDGED;
            case RUNNING -> StageRunStatus.RUNNING;
            case TO_BE_CANCELLED -> StageRunStatus.TO_BE_CANCELLED;
            case CANCELLED -> StageRunStatus.CANCELLED;
            case FAILED -> StageRunStatus.FAILED;
            case SUCCESS -> StageRunStatus.SUCCESS;
        };
    }
}
