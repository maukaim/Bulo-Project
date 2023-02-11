package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunAncestorAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunDependencyAdapter;
import com.maukaim.bulo.executors.data.runs.StageRunAncestor;
import com.maukaim.bulo.executors.data.runs.StageRunDependency;
import com.maukaim.bulo.executors.io.in.model.StageRunAncestorDto;
import com.maukaim.bulo.executors.io.in.model.StageRunDependencyDto;

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
