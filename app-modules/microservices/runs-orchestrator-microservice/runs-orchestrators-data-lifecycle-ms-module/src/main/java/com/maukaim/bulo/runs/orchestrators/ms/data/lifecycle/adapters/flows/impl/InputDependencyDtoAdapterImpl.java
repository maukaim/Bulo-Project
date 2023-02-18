package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.InputDependencyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.InputProviderDtoAdapter;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flow.InputDependencyDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flow.InputProviderDto;

import java.util.Set;
import java.util.stream.Collectors;

public class InputDependencyDtoAdapterImpl implements InputDependencyDtoAdapter {
    private final InputProviderDtoAdapter inputProviderDtoAdapter;

    public InputDependencyDtoAdapterImpl(InputProviderDtoAdapter inputProviderDtoAdapter) {
        this.inputProviderDtoAdapter = inputProviderDtoAdapter;
    }

    @Override
    public InputDependencyDto adapte(InputDependency inputDependency) {
        return new InputDependencyDto(
                inputDependency.getInputId(),
                resolve(inputDependency.getInputProviders())
        );
    }

    private Set<InputProviderDto> resolve(Set<InputProvider> inputProviders) {
        return inputProviders == null ? null : inputProviders.stream()
                .map(this.inputProviderDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
