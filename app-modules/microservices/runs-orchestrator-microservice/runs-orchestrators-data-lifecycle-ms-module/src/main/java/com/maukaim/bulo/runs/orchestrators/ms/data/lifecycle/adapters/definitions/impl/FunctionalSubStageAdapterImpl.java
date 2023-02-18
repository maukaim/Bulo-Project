package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.impl;

import com.maukaim.bulo.runs.orchestrators.data.definition.FsStage;
import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.FunctionalSubStageAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.IoDependencyAdapter;
import com.maukaim.bulo.io.runs.orchestrators.models.definition.FsStageDto;
import com.maukaim.bulo.io.runs.orchestrators.models.definition.IoDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FunctionalSubStageAdapterImpl implements FunctionalSubStageAdapter {
    private final IoDependencyAdapter ioDependencyAdapter;

    public FunctionalSubStageAdapterImpl(IoDependencyAdapter ioDependencyAdapter) {
        this.ioDependencyAdapter = ioDependencyAdapter;
    }

    @Override
    public FsStage adapte(FsStageDto dto) {
        return new FsStage(dto.getFsStageId(),
                resolve(dto.getIoDependencies()));
    }

    private Set<IoDependency> resolve(Set<IoDependencyDto> ioDependencies) {
        return ioDependencies == null ? Set.of() : ioDependencies.stream()
                .map(this.ioDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
