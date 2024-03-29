package com.maukaim.bulo.data.lifecycle.flows.client.impl;

import com.maukaim.bulo.data.lifecycle.flows.client.InputProviderDtoAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.IoDependencyDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.io.flows.client.model.InputProviderDto;
import com.maukaim.bulo.io.flows.client.model.InputDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class IoDependencyDtoAdapterImpl implements IoDependencyDtoAdapter {
    private final InputProviderDtoAdapter inputProviderDtoAdapter;

    public IoDependencyDtoAdapterImpl(InputProviderDtoAdapter inputProviderDtoAdapter) {
        this.inputProviderDtoAdapter = inputProviderDtoAdapter;
    }

    @Override
    public InputDependencyDto adapte(IoDependency ioDependency) {
        return new InputDependencyDto(
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
