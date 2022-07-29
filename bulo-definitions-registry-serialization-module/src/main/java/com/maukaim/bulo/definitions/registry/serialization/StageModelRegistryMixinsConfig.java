package com.maukaim.bulo.definitions.registry.serialization;

import com.maukaim.bulo.definitions.registry.io.DeclareTechnicalStageDefinitionEvent;
import com.maukaim.bulo.definitions.registry.io.model.StageInputDefinition;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.io.model.StageOutputDefinition;
import com.maukaim.bulo.definitions.registry.serialization.mixins.DeclareTechnicalStageDefinitionEventMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.StageInputDefinitionMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.TechnicalStageDefinitionMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.StageOutputDefinitionMixIn;

import java.util.Map;

public class StageModelRegistryMixinsConfig {

    public static Map<Class<?>, Class<?>> DEFINITIONS_REGISTRY_JACKSON_MIXIN = Map.of(
            DeclareTechnicalStageDefinitionEvent.class, DeclareTechnicalStageDefinitionEventMixIn.class,
            TechnicalStageDefinition.class, TechnicalStageDefinitionMixIn.class,
            StageInputDefinition.class, StageInputDefinitionMixIn.class,
            StageOutputDefinition.class, StageOutputDefinitionMixIn.class
    );
}
