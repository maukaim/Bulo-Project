package com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl;

import com.maukaim.bulo.io.definitions.client.dtos.functional.InputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.InputProviderAdapter;

public class InputProviderAdapterImpl implements InputProviderAdapter {
    @Override
    public InputProvider adapte(InputProviderDto dto) {
        return new InputProvider(
                dto.getFsStageId(),
                dto.getOutputIds()
        );
    }
}
