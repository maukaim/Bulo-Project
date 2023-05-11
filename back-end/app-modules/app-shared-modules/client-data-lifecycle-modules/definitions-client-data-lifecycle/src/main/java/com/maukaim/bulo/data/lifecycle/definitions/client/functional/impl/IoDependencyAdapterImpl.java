package com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl;

import com.maukaim.bulo.io.definitions.client.dtos.functional.InputProviderDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.InputProviderAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.IoDependencyAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class IoDependencyAdapterImpl implements IoDependencyAdapter {
    private final InputProviderAdapter inputProviderAdapter;

    public IoDependencyAdapterImpl(InputProviderAdapter inputProviderAdapter) {
        this.inputProviderAdapter = inputProviderAdapter;
    }

    @Override
    public IoDependency adapte(IoDependencyDto dto) {
        return new IoDependency(
                dto.getInputId(),
                adapte(dto.getInputProviders())
        );
    }

    private Set<InputProvider> adapte(Set<InputProviderDto> inputProviders) {
        return inputProviders == null ? Set.of() : inputProviders.stream()
                .map(this.inputProviderAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
