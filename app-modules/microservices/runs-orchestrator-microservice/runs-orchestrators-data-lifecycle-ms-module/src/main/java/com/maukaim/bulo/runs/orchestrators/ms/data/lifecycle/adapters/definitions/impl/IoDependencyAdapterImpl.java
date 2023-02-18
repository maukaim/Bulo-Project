package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.impl;

import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;
import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.InputProviderAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.IoDependencyAdapter;
import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.InputProviderDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.IoDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class IoDependencyAdapterImpl implements IoDependencyAdapter {
    private final InputProviderAdapter inputProviderAdapter;

    public IoDependencyAdapterImpl(InputProviderAdapter inputProviderAdapter) {
        this.inputProviderAdapter = inputProviderAdapter;
    }

    @Override
    public IoDependency adapte(IoDependencyDto dto) {
        return new IoDependency(dto.getInputId(),
                resolve(dto.getInputProviders()));
    }

    private Set<InputProvider> resolve(Set<InputProviderDto> inputProviders) {
        return inputProviders == null ? Set.of() : inputProviders.stream()
                .map(this.inputProviderAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
