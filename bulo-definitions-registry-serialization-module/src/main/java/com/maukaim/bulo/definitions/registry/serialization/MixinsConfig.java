package com.maukaim.bulo.definitions.registry.serialization;

import com.maukaim.bulo.definitions.registry.io.TechnicalStageDefinitionDeclarationEvent;
import com.maukaim.bulo.definitions.registry.io.model.ParameterDefinition;
import com.maukaim.bulo.definitions.registry.io.model.StageInputDefinition;
import com.maukaim.bulo.definitions.registry.io.model.StageOutputDefinition;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.serialization.mixins.*;

import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> DEFINITIONS_REGISTRY_JACKSON_MIXIN = Map.of(
            TechnicalStageDefinitionDeclarationEvent.class, TechnicalStageDefinitionDeclarationEventMixIn.class,
            TechnicalStageDefinition.class, TechnicalStageDefinitionMixIn.class,
            StageInputDefinition.class, StageInputDefinitionMixIn.class,
            StageOutputDefinition.class, StageOutputDefinitionMixIn.class,
            ParameterDefinition.class, ParameterDefinitionMixIn.class
    );
}
