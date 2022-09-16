package com.maukaim.bulo.executors.io.out;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.executors.io.out.model.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionDeclarationEvent implements ExternalEvent {
    private String stageExecutorId;
    private StageDefinitionDto stageDefinitionDTO;
    private Instant instant;

    public StageDefinitionDeclarationEvent(String stageExecutorId, StageDefinitionDto stageDefinitionDTO, Instant instant) {
        this.stageExecutorId = stageExecutorId;
        this.stageDefinitionDTO = stageDefinitionDTO;
        this.instant = instant;
    }

    public String getStageExecutorId() {
        return stageExecutorId;
    }

    public StageDefinitionDto getTechnicalStageDefinition() {
        return stageDefinitionDTO;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "TechnicalStageDefinitionDeclarationEvent{" +
                "stageExecutorId='" + stageExecutorId + '\'' +
                ", technicalStageDefinitionDTO=" + stageDefinitionDTO +
                ", instant=" + instant +
                '}';
    }
}
