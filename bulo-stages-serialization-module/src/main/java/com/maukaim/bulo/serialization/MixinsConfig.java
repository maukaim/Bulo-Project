package com.maukaim.bulo.serialization;



import com.maukaim.bulo.io.CreateStageEvent;
import com.maukaim.bulo.io.DeleteStageEvent;
import com.maukaim.bulo.io.StageUpdateEvent;
import com.maukaim.bulo.io.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.io.definitions.ParameterDefinitionData;
import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionData;
import com.maukaim.bulo.io.stages.FunctionalStageData;
import com.maukaim.bulo.io.stages.ParameterData;
import com.maukaim.bulo.io.stages.StageData;
import com.maukaim.bulo.io.stages.TechnicalStageData;
import com.maukaim.bulo.serialization.mixins.*;

import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> STAGES_SERVICE_JACKSON_MIXIN = Map.of(
            CreateStageEvent.class, CreateStageEventMixin.class,
            TechnicalStageDefinitionEvent.class, TechnicalStageDefinitionEventMixin.class,
            StageUpdateEvent.class, StageUpdateEventMixin.class,
            DeleteStageEvent.class, DeleteStageEventMixin.class,
            FunctionalStageData.class, FunctionalStageDataMixin.class,
            ParameterData.class, ParameterDataMixin.class,
            TechnicalStageData.class, TechnicalStageDataMixin.class,
            TechnicalStageDefinitionData.class, TechnicalStageDefinitionDataMixin.class,
            ParameterDefinitionData.class, ParameterDefinitionDataMixin.class,
            StageData.class, StageDataMixin.class
    );
}
