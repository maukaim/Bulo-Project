package com.maukaim.bulo.executor.io;

import com.maukaim.bulo.executor.io.out.StageDefinitionDeclarationEvent;

public interface StageDefinitionDeclarationEventPublisher {
    boolean publish(StageDefinitionDeclarationEvent event);
}
