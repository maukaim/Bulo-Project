package com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl;

import com.maukaim.bulo.io.definitions.client.dtos.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.FunctionalSubStageAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.IoDependencyAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class FunctionalSubStageAdapterImpl implements FunctionalSubStageAdapter {
    private final IoDependencyAdapter ioDependencyAdapter;

    public FunctionalSubStageAdapterImpl(IoDependencyAdapter ioDependencyAdapter) {
        this.ioDependencyAdapter = ioDependencyAdapter;
    }

    @Override
    public FsStage adapte(FsStageDto dto) {
        return new FsStage(
                dto.getFsStageId(),
                adapte(dto.getIoDependencies())
        );
    }

    private Set<IoDependency> adapte(Set<IoDependencyDto> ioDependencies) {
        return ioDependencies == null ? Set.of() : ioDependencies.stream()
                .map(this.ioDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
