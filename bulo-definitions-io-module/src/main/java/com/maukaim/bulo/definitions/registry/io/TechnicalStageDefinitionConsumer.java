package com.maukaim.bulo.definitions.registry.io;

public interface TechnicalStageDefinitionConsumer<T> {

    T consume(TechnicalStageDefinitionDeclarationEvent event);
}
