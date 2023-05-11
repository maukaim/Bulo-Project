package com.maukaim.bulo.data.lifecycle.definitions.client.impl;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.data.lifecycle.data.types.client.IoTypeAdapter;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageOutputDefinitionAdapter;
import com.maukaim.bulo.io.definitions.client.dtos.StageOutputDefinitionDto;

public class StageOutputDefinitionAdapterImpl implements StageOutputDefinitionAdapter {
    private final IoTypeAdapter ioTypeAdapter;

    public StageOutputDefinitionAdapterImpl(IoTypeAdapter ioTypeAdapter) {
        this.ioTypeAdapter = ioTypeAdapter;
    }

    @Override
    public StageOutputDefinition adapte(StageOutputDefinitionDto dto) {
        return new StageOutputDefinition(resolve(dto.getIoType()));
    }

    private IoType resolve(IoTypeDto dto) {
        return this.ioTypeAdapter.adapte(dto);
    }
}
