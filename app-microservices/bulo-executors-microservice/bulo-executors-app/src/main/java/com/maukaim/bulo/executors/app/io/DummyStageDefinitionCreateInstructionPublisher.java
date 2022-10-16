package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.commons.io.instructions.TechnicalStageDefinitionCreateInstruction;
import com.maukaim.bulo.executors.io.StageDefinitionCreateInstructionPublisher;
import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;

public class DummyStageDefinitionCreateInstructionPublisher implements StageDefinitionCreateInstructionPublisher {
    private final SystemConnector systemConnector;

    public DummyStageDefinitionCreateInstructionPublisher(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(TechnicalStageDefinitionCreateInstruction event) {
        System.out.println("Publish event : " + event);
        return this.systemConnector.sendToConsumers(EventType.DEF_CREATE_INSTRUCTION, event);
    }
}
