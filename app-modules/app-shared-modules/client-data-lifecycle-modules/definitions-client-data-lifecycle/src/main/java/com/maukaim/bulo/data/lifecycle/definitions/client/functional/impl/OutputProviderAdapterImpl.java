package com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl;

import com.maukaim.bulo.data.lifecycle.definitions.client.functional.OutputProviderAdapter;
import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;

public class OutputProviderAdapterImpl implements OutputProviderAdapter {
    @Override
    public OutputProvider adapte(OutputProviderDto dto) {
        return new OutputProvider(
                dto.getContextStageId(),
                dto.getOutputIds()
        );
    }
}
