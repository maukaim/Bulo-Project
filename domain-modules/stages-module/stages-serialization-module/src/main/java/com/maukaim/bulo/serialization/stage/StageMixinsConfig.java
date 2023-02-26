package com.maukaim.bulo.serialization.stage;


import com.maukaim.bulo.io.stages.client.CreateStageInstruction;
import com.maukaim.bulo.io.stages.client.DeleteStageInstruction;
import com.maukaim.bulo.io.stages.client.model.FunctionalStageDto;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;
import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.serialization.stage.mixins.CreateStageInstructionMixIn;
import com.maukaim.bulo.serialization.stage.mixins.DeleteStageEventMixIn;
import com.maukaim.bulo.serialization.stage.mixins.FunctionalStageDtoMixIn;
import com.maukaim.bulo.serialization.stage.mixins.ParameterDtoMixIn;
import com.maukaim.bulo.serialization.stage.mixins.StageDtoMixIn;
import com.maukaim.bulo.serialization.stage.mixins.StageUpdateEventMixIn;
import com.maukaim.bulo.serialization.stage.mixins.TechnicalStageDtoMixIn;

import java.util.Map;

public class StageMixinsConfig {

    public static Map<Class<?>, Class<?>> STAGES_SERVICE_JACKSON_MIXIN = Map.of(
            CreateStageInstruction.class, CreateStageInstructionMixIn.class,
            StageUpdateEvent.class, StageUpdateEventMixIn.class,
            DeleteStageInstruction.class, DeleteStageEventMixIn.class,
            FunctionalStageDto.class, FunctionalStageDtoMixIn.class,
            ParameterDto.class, ParameterDtoMixIn.class,
            TechnicalStageDto.class, TechnicalStageDtoMixIn.class,
            StageDto.class, StageDtoMixIn.class
    );
}
