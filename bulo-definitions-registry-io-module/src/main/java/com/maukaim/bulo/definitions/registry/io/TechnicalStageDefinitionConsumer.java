package com.maukaim.bulo.definitions.registry.io;

import com.maukaim.bulo.definitions.registry.io.TechnicalStageDefinitionDeclarationEvent;

public interface TechnicalStageDefinitionConsumer<T> {

    T register(TechnicalStageDefinitionDeclarationEvent event);
}
