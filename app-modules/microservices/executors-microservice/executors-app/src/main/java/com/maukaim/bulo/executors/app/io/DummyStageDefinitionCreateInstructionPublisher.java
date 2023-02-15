package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.definitions.shared.instructions.CreateStageDefinitionInstruction;
import com.maukaim.bulo.io.executors.StageDefinitionCreateInstructionPublisher;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;

public class DummyStageDefinitionCreateInstructionPublisher implements StageDefinitionCreateInstructionPublisher {
    private final SystemConnector<ServiceEventType> systemConnector;

    public DummyStageDefinitionCreateInstructionPublisher(SystemConnector<ServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(CreateStageDefinitionInstruction event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, ServiceEventType.DEF_CREATE_INSTRUCTION)
                .isEmpty();
    }
}
