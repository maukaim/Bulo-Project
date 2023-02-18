package com.maukaim.bulo.flows.ms.data.lifecycle.adapters.impl;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.common.data.lifecycle.IoTypeAdapter;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;
import com.maukaim.bulo.io.flows.system.definition.StageOutputDefinitionDto;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageOutputDefinitionAdapter;

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
