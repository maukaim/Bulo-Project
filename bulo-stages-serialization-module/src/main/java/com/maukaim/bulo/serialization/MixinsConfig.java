package com.maukaim.bulo.serialization;



import com.maukaim.bulo.stages.io.events.CreateStageEvent;
import com.maukaim.bulo.stages.io.events.DeleteStageEvent;
import com.maukaim.bulo.stages.io.events.StageUpdateEvent;
import com.maukaim.bulo.stages.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.stages.io.models.definitions.ParameterDefinitionDto;
import com.maukaim.bulo.stages.io.models.definitions.TechnicalStageDefinitionDto;
import com.maukaim.bulo.stages.io.models.stages.FunctionalStageDto;
import com.maukaim.bulo.stages.io.models.stages.ParameterDto;
import com.maukaim.bulo.stages.io.models.stages.StageDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import com.maukaim.bulo.serialization.mixins.*;

import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> STAGES_SERVICE_JACKSON_MIXIN = Map.of(
            CreateStageEvent.class, CreateStageEventMixin.class,
            TechnicalStageDefinitionEvent.class, TechnicalStageDefinitionEventMixin.class,
            StageUpdateEvent.class, StageUpdateEventMixin.class,
            DeleteStageEvent.class, DeleteStageEventMixin.class,
            FunctionalStageDto.class, FunctionalStageDataMixin.class,
            ParameterDto.class, ParameterDataMixin.class,
            TechnicalStageDto.class, TechnicalStageDataMixin.class,
            TechnicalStageDefinitionDto.class, TechnicalStageDefinitionDataMixin.class,
            ParameterDefinitionDto.class, ParameterDefinitionDataMixin.class,
            StageDto.class, StageDataMixin.class
    );
}
