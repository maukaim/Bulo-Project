package com.maukaim.bulo.definitions.data.lifecycle.functional.impl;

import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;
import com.maukaim.bulo.definitions.data.lifecycle.functional.FunctionalSubStageDtoAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.functional.IoDependencyDtoAdapter;

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
