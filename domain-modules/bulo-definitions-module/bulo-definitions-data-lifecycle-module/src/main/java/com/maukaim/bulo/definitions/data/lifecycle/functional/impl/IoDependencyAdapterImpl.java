package com.maukaim.bulo.definitions.data.lifecycle.functional.impl;

import com.maukaim.bulo.commons.io.instructions.models.functional.InputProviderDto;
import com.maukaim.bulo.commons.io.instructions.models.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;
import com.maukaim.bulo.definitions.data.lifecycle.functional.InputProviderAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.functional.IoDependencyAdapter;

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
