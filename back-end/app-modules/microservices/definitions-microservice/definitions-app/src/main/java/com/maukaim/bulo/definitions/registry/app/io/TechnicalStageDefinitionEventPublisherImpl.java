package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.definitions.ms.data.lifecycle.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.io.definitions.system.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;

import static com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType.EXECUTOR_UPDATE;

public class TechnicalStageDefinitionEventPublisherImpl implements TechnicalStageDefinitionEventPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public TechnicalStageDefinitionEventPublisherImpl(SystemConnector<MicroServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageDefinitionEvent event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, MicroServiceEventType.DEF_UPDATE)
                .isEmpty();
    }

    @Override
    public boolean publish(ExecutorUpdateEvent event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, EXECUTOR_UPDATE)
                .isEmpty();
    }
}
