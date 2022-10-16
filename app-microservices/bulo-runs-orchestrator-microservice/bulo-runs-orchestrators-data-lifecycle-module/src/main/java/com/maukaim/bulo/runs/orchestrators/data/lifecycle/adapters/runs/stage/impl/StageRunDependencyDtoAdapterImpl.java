package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAncestorDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunDependencyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class StageRunDependencyDtoAdapterImpl implements StageRunDependencyDtoAdapter {
    private final StageRunAncestorDtoAdapter stageRunAncestorDtoAdapter;

    public StageRunDependencyDtoAdapterImpl(StageRunAncestorDtoAdapter stageRunAncestorDtoAdapter) {
        this.stageRunAncestorDtoAdapter = stageRunAncestorDtoAdapter;
    }

    @Override
    public StageRunDependencyDto adapte(StageRunDependency dependency) {
        return new StageRunDependencyDto(
                dependency.getInputId(),
                resolve(dependency.getAncestors())
        );
    }

    private Set<StageRunAncestorDto> resolve(Set<StageRunAncestor> ancestors) {
        return ancestors == null ? Set.of() : ancestors.stream()
                .map(this.stageRunAncestorDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
