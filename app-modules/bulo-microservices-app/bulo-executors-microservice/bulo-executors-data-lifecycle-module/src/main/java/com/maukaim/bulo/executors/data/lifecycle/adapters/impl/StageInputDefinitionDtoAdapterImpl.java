package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.common.data.lifecycle.IoTypeDtoAdapter;
import com.maukaim.bulo.commons.io.data.types.IoTypeDto;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.runners.api.models.StageInputDefinition;

public class StageInputDefinitionDtoAdapterImpl implements StageInputDefinitionDtoAdapter {
    private final IoTypeDtoAdapter ioTypeDtoAdapter;

    public StageInputDefinitionDtoAdapterImpl(IoTypeDtoAdapter ioTypeDtoAdapter) {
        this.ioTypeDtoAdapter = ioTypeDtoAdapter;
    }

    @Override
    public StageInputDefinitionDto adapte(StageInputDefinition inputDefinition) {
        return new StageInputDefinitionDto(resolve(inputDefinition.getType()));
    }

    private IoTypeDto resolve(IoType type) {
        return this.ioTypeDtoAdapter.adapte(type);
    }
}
