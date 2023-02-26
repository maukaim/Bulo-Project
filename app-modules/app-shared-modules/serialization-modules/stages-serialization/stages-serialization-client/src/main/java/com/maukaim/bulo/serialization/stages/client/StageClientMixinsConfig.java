package com.maukaim.bulo.serialization.stages.client;


import com.maukaim.bulo.io.stages.client.CreateStageInstruction;
import com.maukaim.bulo.io.stages.client.DeleteStageInstruction;
import com.maukaim.bulo.io.stages.client.model.FunctionalStageDto;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;
import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;
import com.maukaim.bulo.serialization.stages.client.mixins.CreateStageInstructionMixIn;
import com.maukaim.bulo.serialization.stages.client.mixins.DeleteStageEventMixIn;
import com.maukaim.bulo.serialization.stages.client.mixins.FunctionalStageDtoMixIn;
import com.maukaim.bulo.serialization.stages.client.mixins.ParameterDtoMixIn;
import com.maukaim.bulo.serialization.stages.client.mixins.StageDtoMixIn;
import com.maukaim.bulo.serialization.stages.client.mixins.TechnicalStageDtoMixIn;

import java.util.Map;

public class StageClientMixinsConfig {

    public static Map<Class<?>, Class<?>> STAGES_SERVICE_JACKSON_MIXIN = Map.of(
            CreateStageInstruction.class, CreateStageInstructionMixIn.class,
            DeleteStageInstruction.class, DeleteStageEventMixIn.class,
            FunctionalStageDto.class, FunctionalStageDtoMixIn.class,
            ParameterDto.class, ParameterDtoMixIn.class,
            TechnicalStageDto.class, TechnicalStageDtoMixIn.class,
            StageDto.class, StageDtoMixIn.class
    );
}
