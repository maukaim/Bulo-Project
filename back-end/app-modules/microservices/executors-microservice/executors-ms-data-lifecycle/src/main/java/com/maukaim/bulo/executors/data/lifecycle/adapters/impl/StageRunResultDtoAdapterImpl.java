package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunResultDtoAdapter;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.data.result.StageRunStatus;
import com.maukaim.bulo.io.executors.system.dtos.StageRunResultDto;
import com.maukaim.bulo.io.executors.system.dtos.StageRunStatusDto;

public class StageRunResultDtoAdapterImpl implements StageRunResultDtoAdapter {
    @Override
    public StageRunResultDto adapte(StageRunResult stageRunResult) {
        return new StageRunResultDto(
                stageRunResult.getStageRunId(),
                resolve(stageRunResult.getStatus()),
                stageRunResult.getOutputs()
        );
    }

    private StageRunStatusDto resolve(StageRunStatus status) {
        return status == null ? null : switch (status){
            case ACKNOWLEDGED -> StageRunStatusDto.ACKNOWLEDGED;
            case RUNNING -> StageRunStatusDto.RUNNING;
            case CANCELLED -> StageRunStatusDto.CANCELLED;
            case FAILED -> StageRunStatusDto.FAILED;
            case SUCCESS -> StageRunStatusDto.SUCCESS;
        };
    }
}
