package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.InputProviderAdapter;
import com.maukaim.bulo.flows.data.lifecycle.IoDependencyAdapter;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.io.flows.system.flow.InputProviderDto;
import com.maukaim.bulo.io.flows.system.flow.IoDependencyDto;

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
                resolveProviders(dto.getInputProviders())
        );
    }

    private Set<InputProvider> resolveProviders(Set<InputProviderDto> inputProviders) {
        return inputProviders == null ? null : inputProviders.stream()
                .map(this.inputProviderAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
