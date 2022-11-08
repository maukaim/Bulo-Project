package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.impl;

import com.maukaim.bulo.runs.orchestrators.data.definition.FsStage;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.FunctionalSubStageAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.StageDefinitionAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.FsStageDto;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.StageDefinitionDto;

import java.util.Set;
import java.util.stream.Collectors;

public class StageDefinitionAdapterImpl implements StageDefinitionAdapter {
    private final FunctionalSubStageAdapter functionalSubStageAdapter;

    public StageDefinitionAdapterImpl(FunctionalSubStageAdapter functionalSubStageAdapter) {
        this.functionalSubStageAdapter = functionalSubStageAdapter;
    }

    @Override
    public FunctionalStageDefinition adapte(StageDefinitionDto dto) {
        return new FunctionalStageDefinition(
                dto.getDefinitionId(),
                resolve(dto.getFunctionalSubStages())
        );
    }

    private Set<FsStage> resolve(Set<FsStageDto> functionalSubStages) {
        return functionalSubStages == null ? Set.of() : functionalSubStages.stream()
                .map(this.functionalSubStageAdapter::adapte)
                .collect(Collectors.toSet());
    }

}
