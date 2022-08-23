package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.impl;

import com.maukaim.bulo.runs.orchestrators.data.models.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.models.StageRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.StageRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.io.both.model.StageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.both.model.StageRunStatusDto;

public class StageRunDtoAdapterImpl implements StageRunDtoAdapter {
    @Override
    public StageRunDto adapte(StageRun stageRun) {
        return new StageRunDto(
                stageRun.getStageRunId(),
                stageRun.getFlowStageId(),
                stageRun.getFlowRunId(),
                resolve(stageRun.getStageRunStatus()),
                stageRun.getExecutorId(),
                stageRun.getStartTime(),
                stageRun.getEndTime()
        );
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
