package com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.impl;

import com.maukaim.bulo.executors.data.runs.StageRunAncestor;
import com.maukaim.bulo.executors.data.runs.StageRunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunAncestorAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunDependencyAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class StageRunDependencyAdapterImpl implements StageRunDependencyAdapter {
    private final StageRunAncestorAdapter stageRunAncestorAdapter;

    public StageRunDependencyAdapterImpl(StageRunAncestorAdapter stageRunAncestorAdapter) {
        this.stageRunAncestorAdapter = stageRunAncestorAdapter;
    }

    @Override
    public StageRunDependency adapte(RunDependency runDependency) {
        return runDependency == null ? null : new StageRunDependency(
                runDependency.getInputId(),
                resolveAncestors(runDependency.getAncestors())
        );
    }

    private Set<StageRunAncestor> resolveAncestors(Set<com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor> ancestors) {
        return ancestors == null ? Set.of() : ancestors.stream()
                .map(this.stageRunAncestorAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
