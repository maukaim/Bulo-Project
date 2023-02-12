package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.common.data.lifecycle.IoTypeAdapter;
import com.maukaim.bulo.commons.io.data.types.IoTypeDto;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageInputDefinitionAdapter;

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
