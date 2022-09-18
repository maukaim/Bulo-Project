package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.validators.StageCreateReport;
import com.maukaim.bulo.stages.io.CreateStageEventConsumer;
import com.maukaim.bulo.stages.io.events.CreateStageEvent;
import com.maukaim.bulo.stages.io.models.stages.FunctionalStageDto;
import com.maukaim.bulo.stages.io.models.stages.StageDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.persistence.adapters.StageAdapter;

public class CreateStageEventConsumerImpl implements CreateStageEventConsumer {
    private final StageService stageService;
    private final StageAdapter stageAdapter;

    public CreateStageEventConsumerImpl(StageService stageService, StageAdapter stageAdapter) {
        this.stageService = stageService;
        this.stageAdapter = stageAdapter;
    }

    @Override
    public String consume(CreateStageEvent event) {
        System.out.println("Consume event: " + event);
        StageDto stageDto = event.getStageDto();
        Stage stage = switch (stageDto.getStageType()) {
            case TECHNICAL -> this.stageAdapter.adapte((TechnicalStageDto) stageDto);
            case FUNCTIONAL -> this.stageAdapter.adapte((FunctionalStageDto) stageDto);
        };
        StageCreateReport stageCreateReport = this.stageService.addStage(stage);
        System.out.println(stageCreateReport);
        //TODO: Need to be passed to the caller, report made for the event producer. This event is intendeed to come from REST call.
        // Concept of sync and async consumers? first its signature is public O consume(I event) and second is public void consume(I event).
        return stageCreateReport.getStageId();
    }
}
