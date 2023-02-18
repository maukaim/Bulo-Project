package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.InputDependencyAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.InputProviderAdapter;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.InputDependencyDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.InputProviderDto;

import java.util.Set;
import java.util.stream.Collectors;

public class InputDependencyAdapterImpl implements InputDependencyAdapter {
    private final InputProviderAdapter inputProviderAdapter;

    public InputDependencyAdapterImpl(InputProviderAdapter inputProviderAdapter) {
        this.inputProviderAdapter = inputProviderAdapter;
    }

    @Override
    public InputDependency adapte(InputDependencyDto dto) {
        return new InputDependency(
                dto.getInputId(),
                resolve(dto.getInputProviders())
        );
    }

    private Set<InputProvider> resolve(Set<InputProviderDto> inputProviders) {
        return inputProviders == null ? null : inputProviders.stream()
                .map(this.inputProviderAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
