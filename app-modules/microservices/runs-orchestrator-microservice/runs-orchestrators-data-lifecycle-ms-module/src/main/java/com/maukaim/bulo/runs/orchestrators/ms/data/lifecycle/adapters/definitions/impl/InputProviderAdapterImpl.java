package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.impl;

import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.InputProviderAdapter;
import com.maukaim.bulo.io.runs.orchestrators.models.definition.InputProviderDto;

public class InputProviderAdapterImpl implements InputProviderAdapter {
    @Override
    public InputProvider adapte(InputProviderDto dto) {
        return new InputProvider(dto.getFsStageId(),
                dto.getOutputIds());
    }
}
