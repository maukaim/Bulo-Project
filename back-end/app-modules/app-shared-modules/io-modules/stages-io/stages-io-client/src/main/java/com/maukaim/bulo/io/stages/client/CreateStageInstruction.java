package com.maukaim.bulo.io.stages.client;

import com.maukaim.bulo.io.shared.ExternalEvent;
import com.maukaim.bulo.io.stages.client.model.StageDto;

import java.time.Instant;

public class CreateStageInstruction implements ExternalEvent {
    private final StageDto stageDto;
    private final Instant instant;

    public CreateStageInstruction(StageDto stageDto, Instant instant) {
        this.stageDto = stageDto;
        this.instant = instant;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public StageDto getStage() {
        return stageDto;
    }
}
