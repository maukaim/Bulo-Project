package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.impl;

import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.InputProviderAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.InputProviderDto;

public class InputProviderAdapterImpl implements InputProviderAdapter {
    @Override
    public InputProvider adapte(InputProviderDto dto) {
        return new InputProvider(dto.getFsStageId(),
                dto.getOutputIds());
    }
}
