package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.impl;

import com.maukaim.bulo.io.definitions.client.dtos.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.FsStage;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.FunctionalSubStageAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.OutputProviderAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.StageDefinitionAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class StageDefinitionAdapterImpl implements StageDefinitionAdapter {
    private final FunctionalSubStageAdapter functionalSubStageAdapter;
    private final OutputProviderAdapter outputProviderAdapter;

    public StageDefinitionAdapterImpl(FunctionalSubStageAdapter functionalSubStageAdapter,
                                      OutputProviderAdapter outputProviderAdapter) {
        this.functionalSubStageAdapter = functionalSubStageAdapter;
        this.outputProviderAdapter = outputProviderAdapter;
    }

    @Override
    public FunctionalStageDefinition adapte(FunctionalStageDefinitionDto dto) {
        return new FunctionalStageDefinition(
                dto.getDefinitionId(),
                resolve(dto.getFunctionalSubStages()),
                resolveOutputProviders(dto.getOutputProviders()));
    }

    private Set<OutputProvider> resolveOutputProviders(Set<OutputProviderDto> outputProviders) {
        return outputProviders == null ? Set.of() : outputProviders.stream()
                .map(this.outputProviderAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private Set<FsStage> resolve(Set<FsStageDto> functionalSubStages) {
        return functionalSubStages == null ? Set.of() : functionalSubStages.stream()
                .map(this.functionalSubStageAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
