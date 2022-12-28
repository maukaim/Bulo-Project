package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.validators.StageCreateReport;
import com.maukaim.bulo.stages.io.CreateStageEventConsumer;
import com.maukaim.bulo.stages.io.events.CreateStageInstruction;
import com.maukaim.bulo.stages.io.models.stages.FunctionalStageDto;
import com.maukaim.bulo.stages.io.models.stages.StageDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.data.lifecycle.StageAdapter;

public class CreateStageEventConsumerImpl implements CreateStageEventConsumer {
    private final StageService stageService;
    private final StageAdapter stageAdapter;

    public CreateStageEventConsumerImpl(StageService stageService, StageAdapter stageAdapter) {
        this.stageService = stageService;
        this.stageAdapter = stageAdapter;
    }

    @Override
    public String consume(CreateStageInstruction event) {
        System.out.println("Consume event: " + event);
        StageDto stageDto = event.getStage();
        Stage stage = switch (stageDto.getStageType()) {
            case TECHNICAL -> this.stageAdapter.adapte((TechnicalStageDto) stageDto);
            case FUNCTIONAL -> this.stageAdapter.adapte((FunctionalStageDto) stageDto);
        };
        StageCreateReport stageCreateReport = this.stageService.addStage(stage);
        System.out.println(stageCreateReport);
        return stageCreateReport.getStageId();
    }
}
