package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.StageRunAncestorAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.StageRunDependencyAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.StageRunAncestorDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.StageRunDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class StageRunDependencyAdapterImpl implements StageRunDependencyAdapter {
    private final StageRunAncestorAdapter stageRunAncestorAdapter;

    public StageRunDependencyAdapterImpl(StageRunAncestorAdapter stageRunAncestorAdapter) {
        this.stageRunAncestorAdapter = stageRunAncestorAdapter;
    }

    @Override
    public RunDependency adapte(StageRunDependencyDto dto) {
        return new RunDependency(
                dto.getInputId(),
                resolve(dto.getAncestors()));
    }

    private Set<StageRunAncestor> resolve(Set<StageRunAncestorDto> dtos) {
        return dtos == null ? Set.of() : dtos.stream()
                .map(this.stageRunAncestorAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
