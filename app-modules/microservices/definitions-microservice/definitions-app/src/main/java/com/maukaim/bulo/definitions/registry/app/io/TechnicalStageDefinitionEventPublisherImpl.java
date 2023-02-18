package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.io.definitions.events.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.events.StageDefinitionEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;

import static com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType.EXECUTOR_UPDATE;

public class TechnicalStageDefinitionEventPublisherImpl implements TechnicalStageDefinitionEventPublisher {
    private final SystemConnector<ServiceEventType> systemConnector;

    public TechnicalStageDefinitionEventPublisherImpl(SystemConnector<ServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageDefinitionEvent event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, ServiceEventType.DEF_UPDATE)
                .isEmpty();
    }

    @Override
    public boolean publish(ExecutorUpdateEvent event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, EXECUTOR_UPDATE)
                .isEmpty();
    }
}
