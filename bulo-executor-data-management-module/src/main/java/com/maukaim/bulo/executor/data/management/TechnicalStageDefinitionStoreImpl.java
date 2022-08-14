package com.maukaim.bulo.executor.data.management;

import com.maukaim.bulo.executor.core.api.TechnicalStageDefinitionStore;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;
import com.maukaim.bulo.executor.data.management.adapters.TechnicalStageDefinitionDtoAdapter;
import com.maukaim.bulo.executor.io.TsdDeclarationEventPublisher;
import com.maukaim.bulo.executor.io.out.TsdDeclarationEvent;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechnicalStageDefinitionStoreImpl implements TechnicalStageDefinitionStore {
    private final static String DUMMY_EXECUTOR_ID = "DUMMY";
    private final TechnicalStageDefinitionDtoAdapter technicalStageDefinitionDtoAdapter;
    private final TsdDeclarationEventPublisher publisher;
    private final Map<String, TechnicalStageDefinition> technicalStageDefinitionById;

    public TechnicalStageDefinitionStoreImpl(TechnicalStageDefinitionDtoAdapter technicalStageDefinitionDtoAdapter,
            TsdDeclarationEventPublisher publisher) {
        this.technicalStageDefinitionDtoAdapter = technicalStageDefinitionDtoAdapter;
        this.publisher = publisher;
        this.technicalStageDefinitionById = new HashMap<>();
    }

    @Override
    public TechnicalStageDefinition getById(String definitionId) {
        return this.technicalStageDefinitionById.get(definitionId);
    }

    @Override
    public TechnicalStageDefinition put(TechnicalStageDefinition technicalStageDefinition) {
        TechnicalStageDefinition definitionStored = this.technicalStageDefinitionById.putIfAbsent(technicalStageDefinition.getTechnicalStageDefinitionId(), technicalStageDefinition);
        this.publish(definitionStored);
        return definitionStored;
    }

    @Override
    public List<TechnicalStageDefinition> putAll(Collection<TechnicalStageDefinition> technicalStageDefinitions) {
        for (TechnicalStageDefinition technicalStageDefinition : technicalStageDefinitions) {
            this.put(technicalStageDefinition);
        }
        return technicalStageDefinitions.stream().toList();
    }

    @Override
    public TechnicalStageDefinition remove(TechnicalStageDefinition definition) {
        return this.technicalStageDefinitionById.remove(definition.getTechnicalStageDefinitionId());
    }

    @Override
    public List<TechnicalStageDefinition> getAll() {
        return this.technicalStageDefinitionById.values().stream().toList();
    }

    private void publish(TechnicalStageDefinition definitionStored) {
        this.publisher.publish(new TsdDeclarationEvent(
                DUMMY_EXECUTOR_ID, this.technicalStageDefinitionDtoAdapter.adapte(definitionStored),
                Instant.now())
        );
    }
}
