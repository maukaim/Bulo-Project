package com.maukaim.bulo.stages.io.events;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.stages.io.models.stages.StageDto;

import java.time.Instant;

public class CreateStageInstruction implements ExternalEvent {
    private StageDto stageDto;
    private Instant instant;

    public CreateStageInstruction(StageDto stageDto, Instant instant) {
        this.stageDto = stageDto;
        this.instant = instant;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public StageDto getStageDto() {
        return stageDto;
    }
}
