package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;

public class TechnicalStageDefinitionEventPublisherImpl implements TechnicalStageDefinitionEventPublisher {
    private final SystemConnector systemConnector;

    public TechnicalStageDefinitionEventPublisherImpl(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(TechnicalStageDefinitionEvent event) {
        System.out.println("Publish event : " + event);
        return this.systemConnector.sendToConsumers(EventType.DEF_UPDATE, event);
    }
}
