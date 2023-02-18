package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.common.data.lifecycle.IoTypeDtoAdapter;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.io.definitions.client.models.StageInputDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageInputDefinitionDtoAdapter;

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
