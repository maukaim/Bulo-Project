package com.maukaim.bulo.executors.io;

import com.maukaim.bulo.executors.io.out.StageDefinitionDeclarationEvent;

public interface StageDefinitionDeclarationEventPublisher {
    boolean publish(StageDefinitionDeclarationEvent event);
}
