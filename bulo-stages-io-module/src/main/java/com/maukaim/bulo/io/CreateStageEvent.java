package com.maukaim.bulo.io;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.io.stages.StageData;

import java.time.Instant;

public class CreateStageEvent implements ExternalEvent {
    private StageData stageData;
    private Instant instant;

    public CreateStageEvent(StageData stageData, Instant instant) {
        this.stageData = stageData;
        this.instant = instant;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public StageData getStageData() {
        return stageData;
    }
}
