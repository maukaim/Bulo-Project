package com.maukaim.bulo.standalone.app.io;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.validators.StageCreateReport;
import com.maukaim.bulo.stages.data.lifecycle.StageAdapter;
import com.maukaim.bulo.stages.io.CreateStageEventConsumer;
import com.maukaim.bulo.stages.io.events.CreateStageInstruction;
import com.maukaim.bulo.stages.io.models.stages.FunctionalStageDto;
import com.maukaim.bulo.stages.io.models.stages.StageDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.Stage;

public class CreateStageInstructionConsumerImpl implements CreateStageEventConsumer {
    private final StageService stageService;
    private final StageAdapter stageAdapter;

    public CreateStageInstructionConsumerImpl(StageService stageService, StageAdapter stageAdapter) {
        this.stageService = stageService;
        this.stageAdapter = stageAdapter;
    }

    @Override
    public String consume(CreateStageInstruction event) {
        System.out.println("Consume event: " + event);
        StageDto stageDto = event.getStageDto();
        Stage stage = switch (stageDto.getStageType()) {
            case TECHNICAL -> this.stageAdapter.adapte((TechnicalStageDto) stageDto);
            case FUNCTIONAL -> this.stageAdapter.adapte((FunctionalStageDto) stageDto);
        };
        StageCreateReport stageCreateReport = this.stageService.addStage(stage);
        System.out.println(stageCreateReport);
        return stageCreateReport.getStageId();
    }
}

//Il faut que les deserializers soient par module ou par ms.
// Comme ca on peut import le deserializer qu'il faut pour les instructions, dans ms ou standalone.
// 1 - Creer un deserialization-ms-module dans les dossiers de ms de Flow, Stages et Triggers.
// 2 - Split MixIns Maps de Stages, Flows et Triggers.
// 3 - Dans MS et dans Standalone, import ce qu'ils ont besoin d'import.
// 4 -
