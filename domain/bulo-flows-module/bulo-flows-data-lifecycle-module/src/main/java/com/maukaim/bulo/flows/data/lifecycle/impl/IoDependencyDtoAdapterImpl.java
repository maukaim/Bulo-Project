package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.InputProviderDtoAdapter;
import com.maukaim.bulo.flows.data.lifecycle.IoDependencyDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.flows.io.flow.InputProviderDto;
import com.maukaim.bulo.flows.io.flow.IoDependencyDto;

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
                resolveProviders(ioDependency.getInputProviders())
        );
    }

    private Set<InputProviderDto> resolveProviders(Set<InputProvider> inputProviders) {
        return inputProviders == null ? null : inputProviders.stream()
                .map(this.inputProviderDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
