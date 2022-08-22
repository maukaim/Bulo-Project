package com.maukaim.bulo.executor.data.management;

import com.maukaim.bulo.executor.core.api.StageDefinitionStore;
import com.maukaim.bulo.executor.core.api.models.StageDefinition;
import com.maukaim.bulo.executor.data.management.adapters.StageDefinitionDtoAdapter;
import com.maukaim.bulo.executor.io.StageDefinitionDeclarationEventPublisher;
import com.maukaim.bulo.executor.io.out.StageDefinitionDeclarationEvent;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StageDefinitionStoreImpl implements StageDefinitionStore {
    private final static String DUMMY_EXECUTOR_ID = "DUMMY";
    private final StageDefinitionDtoAdapter stageDefinitionDtoAdapter;
    private final StageDefinitionDeclarationEventPublisher publisher;
    private final Map<String, StageDefinition> technicalStageDefinitionById;

    public StageDefinitionStoreImpl(StageDefinitionDtoAdapter stageDefinitionDtoAdapter,
                                    StageDefinitionDeclarationEventPublisher publisher,
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
        this.technicalStageDefinitionById.putIfAbsent(stageDefinition.getTechnicalStageDefinitionId(), stageDefinition);
        StageDefinition definitionStored = this.technicalStageDefinitionById.get(stageDefinition.getTechnicalStageDefinitionId());
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
        return this.technicalStageDefinitionById.remove(definition.getTechnicalStageDefinitionId());
    }

    @Override
    public List<StageDefinition> getAll() {
        return this.technicalStageDefinitionById.values().stream().toList();
    }

    private void publish(StageDefinition definitionStored) {
        this.publisher.publish(new StageDefinitionDeclarationEvent(
                DUMMY_EXECUTOR_ID, this.stageDefinitionDtoAdapter.adapte(definitionStored),
                Instant.now())
        );
    }
}
