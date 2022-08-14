package com.maukaim.bulo.executor.io.out;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.executor.io.out.model.TechnicalStageDefinitionDto;

import java.time.Instant;

public class TsdDeclarationEvent implements ExternalEvent {
    private String stageExecutorId;
    private TechnicalStageDefinitionDto technicalStageDefinitionDTO;
    private Instant instant;

    public TsdDeclarationEvent(String stageExecutorId, TechnicalStageDefinitionDto technicalStageDefinitionDTO, Instant instant) {
        this.stageExecutorId = stageExecutorId;
        this.technicalStageDefinitionDTO = technicalStageDefinitionDTO;
        this.instant = instant;
    }

    public String getStageExecutorId() {
        return stageExecutorId;
    }

    public TechnicalStageDefinitionDto getTechnicalStageDefinition() {
        return technicalStageDefinitionDTO;
    }

    @Override
    public Instant getInstant() {
        return null;
    }

    @Override
    public String toString() {
        return "TechnicalStageDefinitionDeclarationEvent{" +
                "stageExecutorId='" + stageExecutorId + '\'' +
                ", technicalStageDefinitionDTO=" + technicalStageDefinitionDTO +
                ", instant=" + instant +
                '}';
    }
}
