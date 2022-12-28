package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.commons.io.instructions.StageDefinitionCreateInstruction;
import com.maukaim.bulo.executors.data.StageDefinitionStore;
import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageDefinitionDtoAdapter;
import com.maukaim.bulo.executors.io.StageDefinitionCreateInstructionPublisher;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StageDefinitionStoreImpl implements StageDefinitionStore {
    private final static String DUMMY_EXECUTOR_ID = "DUMMY";
    private final StageDefinitionDtoAdapter stageDefinitionDtoAdapter;
    private final StageDefinitionCreateInstructionPublisher publisher;
    private final Map<String, StageDefinition> technicalStageDefinitionById;

    public StageDefinitionStoreImpl(StageDefinitionDtoAdapter stageDefinitionDtoAdapter,
                                    StageDefinitionCreateInstructionPublisher publisher,
                                    Map<String, StageDefinition> initialCache) {
        this.stageDefinitionDtoAdapter = stageDefinitionDtoAdapter;
        this.publisher = publisher;
        this.technicalStageDefinitionById = new HashMap<>(initialCache);
    }

    @Override
    public StageDefinition getById(String definitionId) {
        return this.technicalStageDefinitionById.get(definitionId);
    }

    @Override
    public StageDefinition put(StageDefinition stageDefinition) {
        this.technicalStageDefinitionById.putIfAbsent(stageDefinition.getDefinitionId(), stageDefinition);
        StageDefinition definitionStored = this.technicalStageDefinitionById.get(stageDefinition.getDefinitionId());
        this.publish(definitionStored);
        return definitionStored;
    }

    @Override
    public List<StageDefinition> putAll(Collection<StageDefinition> stageDefinitions) {
        for (StageDefinition stageDefinition : stageDefinitions) {
            this.put(stageDefinition);
        }
        return stageDefinitions.stream().toList();
    }

    @Override
    public StageDefinition remove(StageDefinition definition) {
        return this.technicalStageDefinitionById.remove(definition.getDefinitionId());
    }

    @Override
    public List<StageDefinition> getAll() {
        return this.technicalStageDefinitionById.values().stream().toList();
    }

    private void publish(StageDefinition definitionStored) {
        this.publisher.publish(new StageDefinitionCreateInstruction(
                DUMMY_EXECUTOR_ID, this.stageDefinitionDtoAdapter.adapte(definitionStored),
                Instant.now())
        );
    }
}
