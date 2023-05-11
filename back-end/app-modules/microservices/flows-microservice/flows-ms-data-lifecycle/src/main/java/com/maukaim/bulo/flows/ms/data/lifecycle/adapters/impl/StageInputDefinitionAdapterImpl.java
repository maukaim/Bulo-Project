package com.maukaim.bulo.flows.ms.data.lifecycle.adapters.impl;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.data.lifecycle.data.types.client.IoTypeAdapter;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageInputDefinitionAdapter;
import com.maukaim.bulo.io.definitions.client.dtos.StageInputDefinitionDto;

public class StageInputDefinitionAdapterImpl implements StageInputDefinitionAdapter {
    private final IoTypeAdapter ioTypeAdapter;

    public StageInputDefinitionAdapterImpl(IoTypeAdapter ioTypeAdapter) {
        this.ioTypeAdapter = ioTypeAdapter;
    }

    @Override
    public StageInputDefinition adapte(StageInputDefinitionDto dto) {
        return new StageInputDefinition(resolve(dto.getIoType()));
    }

    private IoType resolve(IoTypeDto dto) {
        return this.ioTypeAdapter.adapte(dto);
    }
}
