package com.maukaim.bulo.mockingbird.connect;

import com.maukaim.bulo.app.commons.endpoints.ClientEventType;
import com.maukaim.bulo.commons.io.instructions.CreateStageDefinitionInstruction;
import com.maukaim.bulo.commons.io.instructions.models.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.flows.io.events.CreateFlowInstruction;
import com.maukaim.bulo.flows.io.flow.FlowDto;
import com.maukaim.bulo.stages.io.events.CreateStageInstruction;
import com.maukaim.bulo.stages.io.models.stages.StageDto;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class User {
    private final Application app;
    private final String userName = "John Doe";

    public User(Application application) {
        this.app = application;
    }

    public List<String> createMultipleStages(StageDto... dtos) {
        List<CreateStageInstruction> createStageInstructions = Arrays.stream(dtos)
                .map(dto -> new CreateStageInstruction(dto, Instant.EPOCH))
                .toList();
        return app.sendAndGetList(createStageInstructions, ClientEventType.STAGE_MULTIPLE_CREATE_INSTRUCTION, String.class);
    }

    public String createStage(StageDto dto) {
        return app.sendAndGet(new CreateStageInstruction(dto, Instant.EPOCH),
                ClientEventType.STAGE_CREATE_INSTRUCTION,
                String.class);
    }

    public String createFunctionalDefinition(FunctionalStageDefinitionDto dto) {
        return app.sendAndGet(new CreateStageDefinitionInstruction(null, dto, Instant.EPOCH),
                ClientEventType.STAGE_DEFINITION_CREATE_INSTRUCTION,
                String.class);

    }

    public String createFlow(FlowDto dto) {
        return app.sendAndGet(new CreateFlowInstruction(dto, Instant.EPOCH),
                ClientEventType.FLOW_CREATE_INSTRUCTION,
                String.class);

    }

    public void triggerFlowRun(TriggerId triggerId) {
        app.sendAndForget(triggerId, ClientEventType.TRIGGER_MANUAL_ONETIME);
    }

    public String getUserName() {
        return userName;
    }
}
