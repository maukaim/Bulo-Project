package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.definitions.io.events.ExecutorUpdateEvent;
import com.maukaim.bulo.definitions.io.events.StageDefinitionEvent;
import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;

import static com.maukaim.bulo.ms.connectivity.EventType.EXECUTOR_UPDATE;

public class TechnicalStageDefinitionEventPublisherImpl implements TechnicalStageDefinitionEventPublisher {
    private final SystemConnector systemConnector;

    public TechnicalStageDefinitionEventPublisherImpl(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageDefinitionEvent event) {
        System.out.println("Publish event : " + event);
        return this.systemConnector.sendToConsumers(EventType.DEF_UPDATE, event);
    }

    @Override
    public boolean publish(ExecutorUpdateEvent event) {
        System.out.println("Publish event : " + event);
        return this.systemConnector.sendToConsumers(EXECUTOR_UPDATE, event);
    }
}
