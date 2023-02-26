package com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl;

import com.maukaim.bulo.data.lifecycle.definitions.client.functional.OutputProviderDtoAdapter;
import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;

public class OutputProviderDtoAdapterImpl implements OutputProviderDtoAdapter {
    @Override
    public OutputProviderDto adapte(OutputProvider outputProvider) {
        return new OutputProviderDto(
                outputProvider.getContextStageId(),
                outputProvider.getOutputIds()
        );
    }
}
