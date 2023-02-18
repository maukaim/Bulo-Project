package com.maukaim.bulo.definitions.data.lifecycle.functional.impl;

import com.maukaim.bulo.io.definitions.client.models.functional.InputProviderDto;
import com.maukaim.bulo.io.definitions.client.models.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;
import com.maukaim.bulo.definitions.data.lifecycle.functional.InputProviderDtoAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.functional.IoDependencyDtoAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class IoDependencyDtoAdapterImpl implements IoDependencyDtoAdapter {
    private final InputProviderDtoAdapter inputProviderDtoAdapter;

    public IoDependencyDtoAdapterImpl(InputProviderDtoAdapter inputProviderDtoAdapter) {
        this.inputProviderDtoAdapter = inputProviderDtoAdapter;
    }

    @Override
    public IoDependencyDto adapte(IoDependency ioDependency) {
        return new IoDependencyDto(
                ioDependency.getInputId(),
                adapte(ioDependency.getInputProviders())
        );
    }

    private Set<InputProviderDto> adapte(Set<InputProvider> inputProviders) {
        return inputProviders == null ? Set.of() : inputProviders.stream()
                .map(this.inputProviderDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
