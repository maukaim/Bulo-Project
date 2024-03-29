package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.shared.ExternalEvent;
import com.maukaim.bulo.io.executors.system.dtos.StageRunResultDto;

import java.time.Instant;

public class StageRunResultEvent implements ExternalEvent {
    private final StageRunResultDto stageRunResult;
    private final Instant instant;

    public StageRunResultEvent(StageRunResultDto stageRunResult, Instant instant) {
        this.stageRunResult = stageRunResult;
        this.instant = instant;
    }

    public StageRunResultDto getStageRunResult() {
        return stageRunResult;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "StageRunResultEvent{" +
                "stageRunResult=" + stageRunResult +
                ", instant=" + instant +
                '}';
    }
}
