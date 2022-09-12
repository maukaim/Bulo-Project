package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAncestorAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunDependencyAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class StageRunDependencyAdapterImpl implements StageRunDependencyAdapter {
    private final StageRunAncestorAdapter stageRunAncestorAdapter;

    public StageRunDependencyAdapterImpl(StageRunAncestorAdapter stageRunAncestorAdapter) {
        this.stageRunAncestorAdapter = stageRunAncestorAdapter;
    }

    @Override
    public StageRunDependency adapte(StageRunDependencyDto dto) {
        return new StageRunDependency(
                dto.getInputId(),
                resolve(dto.getAncestors()));
    }

    private Set<StageRunAncestor> resolve(Set<StageRunAncestorDto> dtos) {
        return dtos == null ? Set.of() : dtos.stream()
                .map(this.stageRunAncestorAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
