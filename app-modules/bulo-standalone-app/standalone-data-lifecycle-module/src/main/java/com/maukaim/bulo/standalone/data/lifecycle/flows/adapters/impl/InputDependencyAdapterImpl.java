package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.impl;

import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.InputDependencyAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.InputProviderAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class InputDependencyAdapterImpl implements InputDependencyAdapter {
    private final InputProviderAdapter inputProviderAdapter;

    public InputDependencyAdapterImpl(InputProviderAdapter inputProviderAdapter) {
        this.inputProviderAdapter = inputProviderAdapter;
    }

    public InputDependency adapte(IoDependency ioDependency) {
        return ioDependency == null ? null : new InputDependency(
                ioDependency.getInputId(),
                resolveInputProviders(ioDependency.getInputProviders()));
    }

    private Set<com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider> resolveInputProviders(Set<InputProvider> inputProviders) {
        return inputProviders == null ? Set.of() : inputProviders.stream()
                .map(inputProviderAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
