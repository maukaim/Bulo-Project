package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunDependencyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunStatus;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class StageRunDtoAdapterImpl implements StageRunDtoAdapter {
    private final StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter;

    public StageRunDtoAdapterImpl(StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter) {
        this.stageRunDependencyDtoAdapter = stageRunDependencyDtoAdapter;
    }

    @Override
    public StageRunDto adapte(StageRun stageRun) {
        return new StageRunDto(
                stageRun.getStageRunId(),
                stageRun.getFlowStageId(),
                stageRun.getFlowRunId(),
                resolve(stageRun.getStageRunStatus()),
                stageRun.getExecutorId(),
                resolve(stageRun.getStageRunDependencies()),
                stageRun.getStartTime(),
                stageRun.getEndTime()
        );
    }

    private Set<StageRunDependencyDto> resolve(Set<StageRunDependency> stageRunDependencies) {
        return stageRunDependencies == null ? Set.of() : stageRunDependencies.stream()
                .map(this.stageRunDependencyDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private StageRunStatusDto resolve(StageRunStatus stageRunStatus) {
        return switch (stageRunStatus){
            case REQUESTED -> StageRunStatusDto.REQUESTED;
            case ACKNOWLEDGED -> StageRunStatusDto.ACKNOWLEDGED;
            case RUNNING -> StageRunStatusDto.RUNNING;
            case CANCELLED -> StageRunStatusDto.CANCELLED;
            case FAILED -> StageRunStatusDto.FAILED;
            case SUCCESS -> StageRunStatusDto.SUCCESS;
        };
    }
}
