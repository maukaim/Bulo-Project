package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl;

import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;
import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.InputProviderAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.IoDependencyAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class IoDependencyAdapterImpl implements IoDependencyAdapter {
    private final InputProviderAdapter inputProviderAdapter;

    public IoDependencyAdapterImpl(InputProviderAdapter inputProviderAdapter) {
        this.inputProviderAdapter = inputProviderAdapter;
    }

    @Override
    public IoDependency adapteOrchestratorModule(com.maukaim.bulo.definitions.data.definition.functional.IoDependency ioDependency) {
        return new IoDependency(
                ioDependency.getInputId(),
                resolve(ioDependency.getInputProviders())
        );
    }

    private Set<InputProvider> resolve(Set<com.maukaim.bulo.definitions.data.definition.functional.InputProvider> inputProviders) {
        return inputProviders == null ? Set.of() : inputProviders.stream()
                .map(this.inputProviderAdapter::adapteOrchestratorModule)
                .collect(Collectors.toSet());
    }
}
