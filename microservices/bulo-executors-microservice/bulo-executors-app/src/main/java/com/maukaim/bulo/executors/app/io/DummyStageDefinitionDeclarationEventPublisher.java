package com.maukaim.bulo.executors.app.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.executors.io.StageDefinitionDeclarationEventPublisher;
import com.maukaim.bulo.executors.io.out.StageDefinitionDeclarationEvent;
import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;

public class DummyStageDefinitionDeclarationEventPublisher implements StageDefinitionDeclarationEventPublisher {
    private final SystemConnector systemConnector;

    public DummyStageDefinitionDeclarationEventPublisher(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageDefinitionDeclarationEvent event) {
        System.out.println("Publish event : " + event);
        return this.systemConnector.sendToConsumers(EventType.DEF_DECLARATION, event);
    }
}
