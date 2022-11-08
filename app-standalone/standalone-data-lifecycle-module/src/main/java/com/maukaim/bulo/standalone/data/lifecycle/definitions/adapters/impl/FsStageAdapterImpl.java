package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl;

import com.maukaim.bulo.runs.orchestrators.data.definition.FsStage;
import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.FsStageAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.IoDependencyAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class FsStageAdapterImpl implements FsStageAdapter {
    private final IoDependencyAdapter ioDependencyAdapter;

    public FsStageAdapterImpl(IoDependencyAdapter ioDependencyAdapter) {
        this.ioDependencyAdapter = ioDependencyAdapter;
    }

    @Override
    public FsStage adapteOrchestratorModule(com.maukaim.bulo.definitions.data.definition.functional.FsStage fsStage) {
        return new FsStage(
                fsStage.getContextualizedId(),
                resolve(fsStage.getIoDependencies())
        );
    }

    private Set<IoDependency> resolve(Set<com.maukaim.bulo.definitions.data.definition.functional.IoDependency> ioDependencies) {
        return ioDependencies == null ? Set.of() : ioDependencies.stream()
                .map(this.ioDependencyAdapter::adapteOrchestratorModule)
                .collect(Collectors.toSet());
    }
}
