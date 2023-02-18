package com.maukaim.bulo.definitions.data.lifecycle.functional.impl;

import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;
import com.maukaim.bulo.definitions.data.lifecycle.functional.OutputProviderAdapter;

public class OutputProviderAdapterImpl implements OutputProviderAdapter {
    @Override
    public OutputProvider adapte(OutputProviderDto dto) {
        return new OutputProvider(
                dto.getContextStageId(),
                dto.getOutputIds()
        );
    }
}
