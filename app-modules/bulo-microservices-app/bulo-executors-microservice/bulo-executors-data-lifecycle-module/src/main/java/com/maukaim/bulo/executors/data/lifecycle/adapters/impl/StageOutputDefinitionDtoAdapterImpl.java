package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.common.data.lifecycle.IoTypeDtoAdapter;
import com.maukaim.bulo.commons.io.data.types.IoTypeDto;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.executors.data.models.StageOutputDefinition;

public class StageOutputDefinitionDtoAdapterImpl implements StageOutputDefinitionDtoAdapter {
    private final IoTypeDtoAdapter ioTypeDtoAdapter;

    public StageOutputDefinitionDtoAdapterImpl(IoTypeDtoAdapter ioTypeDtoAdapter) {
        this.ioTypeDtoAdapter = ioTypeDtoAdapter;
    }

    @Override
    public StageOutputDefinitionDto adapte(StageOutputDefinition outputDefinition) {
        return new StageOutputDefinitionDto(resolve(outputDefinition.getType()));
    }

    private IoTypeDto resolve(IoType ioType) {
        return this.ioTypeDtoAdapter.adapte(ioType);
    }
}
