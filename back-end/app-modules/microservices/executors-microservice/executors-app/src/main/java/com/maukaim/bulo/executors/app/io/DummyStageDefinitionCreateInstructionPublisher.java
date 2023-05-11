package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;
import com.maukaim.bulo.executors.data.lifecycle.StageDefinitionCreateInstructionPublisher;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

public class DummyStageDefinitionCreateInstructionPublisher implements StageDefinitionCreateInstructionPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public DummyStageDefinitionCreateInstructionPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(CreateStageDefinitionInstruction event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, MicroServiceEventType.DEF_CREATE_INSTRUCTION)
                .isEmpty();
    }
}
