package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunResultAdapter;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.data.result.StageRunStatus;
import com.maukaim.bulo.io.executors.system.out.model.StageRunResultDto;
import com.maukaim.bulo.io.executors.system.out.model.StageRunStatusDto;

import java.util.HashMap;

public class StageRunResultAdapterImpl implements StageRunResultAdapter {
    @Override
    public StageRunResult adapte(StageRunResultDto dto) {
        return new StageRunResult(
                dto.getStageRunId(),
                resolve(dto.getStatus()),
                new HashMap<>(dto.getOutputsByName())
        );
    }

    private StageRunStatus resolve(StageRunStatusDto status) {
        return status == null ? null : switch(status){
            case ACKNOWLEDGED -> StageRunStatus.ACKNOWLEDGED;
            case RUNNING -> StageRunStatus.RUNNING;
            case CANCELLED -> StageRunStatus.CANCELLED;
            case FAILED -> StageRunStatus.FAILED;
            case SUCCESS -> StageRunStatus.SUCCESS;
        };
    }
}
