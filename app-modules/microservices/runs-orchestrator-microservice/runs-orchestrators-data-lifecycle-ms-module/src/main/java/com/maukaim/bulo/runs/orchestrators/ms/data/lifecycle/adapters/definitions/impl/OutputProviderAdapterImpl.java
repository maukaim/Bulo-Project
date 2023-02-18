package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.impl;

import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.OutputProviderAdapter;

public class OutputProviderAdapterImpl implements OutputProviderAdapter {
    @Override
    public OutputProvider adapte(OutputProviderDto dto) {
        return new OutputProvider(dto.getContextStageId(), dto.getOutputIds());
    }
}
