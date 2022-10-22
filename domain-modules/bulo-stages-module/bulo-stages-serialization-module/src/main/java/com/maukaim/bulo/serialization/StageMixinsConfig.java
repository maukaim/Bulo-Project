package com.maukaim.bulo.serialization;



import com.maukaim.bulo.stages.io.events.CreateStageInstruction;
import com.maukaim.bulo.stages.io.events.DeleteStageInstruction;
import com.maukaim.bulo.stages.io.events.StageUpdateEvent;
import com.maukaim.bulo.stages.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.stages.io.models.definitions.ParameterDefinitionDto;
import com.maukaim.bulo.stages.io.models.definitions.StageDefinitionDto;
import com.maukaim.bulo.stages.io.models.stages.FunctionalStageDto;
import com.maukaim.bulo.stages.io.models.stages.ParameterDto;
import com.maukaim.bulo.stages.io.models.stages.StageDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import com.maukaim.bulo.serialization.mixins.*;

import java.util.Map;

public class StageMixinsConfig {

    public static Map<Class<?>, Class<?>> STAGES_SERVICE_JACKSON_MIXIN = Map.of(
            CreateStageInstruction.class, CreateStageInstructionMixin.class,
            TechnicalStageDefinitionEvent.class, TechnicalStageDefinitionEventMixin.class,
            StageUpdateEvent.class, StageUpdateEventMixin.class,
            DeleteStageInstruction.class, DeleteStageEventMixin.class,
            FunctionalStageDto.class, FunctionalStageDtoMixin.class,
            ParameterDto.class, ParameterDtoMixin.class,
            TechnicalStageDto.class, TechnicalStageDtoMixin.class,
            StageDefinitionDto.class, TechnicalStageDefinitionDtoMixin.class,
            ParameterDefinitionDto.class, ParameterDefinitionDtoMixin.class,
            StageDto.class, StageDtoMixin.class
    );
}
