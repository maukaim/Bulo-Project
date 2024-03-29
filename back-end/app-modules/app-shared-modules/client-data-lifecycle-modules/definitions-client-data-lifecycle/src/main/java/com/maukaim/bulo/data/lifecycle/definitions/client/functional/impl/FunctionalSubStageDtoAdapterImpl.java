package com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl;

import com.maukaim.bulo.io.definitions.client.dtos.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.FunctionalSubStageDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.IoDependencyDtoAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class FunctionalSubStageDtoAdapterImpl implements FunctionalSubStageDtoAdapter {
    private final IoDependencyDtoAdapter ioDependencyDtoAdapter;

    public FunctionalSubStageDtoAdapterImpl(IoDependencyDtoAdapter ioDependencyDtoAdapter) {
        this.ioDependencyDtoAdapter = ioDependencyDtoAdapter;
    }

    @Override
    public FsStageDto adapte(FsStage fsStage) {
        return new FsStageDto(
                fsStage.getContextualizedId(),
                adapte(fsStage.getIoDependencies())
        );
    }

    private Set<IoDependencyDto> adapte(Set<IoDependency> ioDependencies) {
        return ioDependencies == null ? Set.of() : ioDependencies.stream()
                .map(this.ioDependencyDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
