package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.impl;

import com.maukaim.bulo.runs.orchestrators.data.models.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.models.StageRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.StageRunAdapter;
import com.maukaim.bulo.runs.orchestrators.io.both.model.StageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.both.model.StageRunStatusDto;

public class StageRunAdapterImpl implements StageRunAdapter {
    @Override
    public StageRun adapte(StageRunDto dto) {
        return new StageRun(
                dto.getStageRunId(),
                dto.getFlowStageId(),
                dto.getFlowRunId(),
                resolve(dto.getStageRunStatus()),
                dto.getExecutorId(),
                dto.getStartTime(),
                dto.getEndTime()
        );
    }

    private StageRunStatus resolve(StageRunStatusDto stageRunStatusDto) {
        return switch (stageRunStatusDto){
            case REQUESTED -> StageRunStatus.REQUESTED;
            case ACKNOWLEDGED -> StageRunStatus.ACKNOWLEDGED;
            case RUNNING -> StageRunStatus.RUNNING;
            case CANCELLED -> StageRunStatus.CANCELLED;
            case FAILED -> StageRunStatus.FAILED;
            case SUCCESS -> StageRunStatus.SUCCESS;
        };
    }
}
