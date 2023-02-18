package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.OutputProviderDtoAdapter;

public class OutputProviderDtoAdapterImpl implements OutputProviderDtoAdapter {
    @Override
    public OutputProviderDto adapte(OutputProvider outputProvider) {
        return new OutputProviderDto(
                outputProvider.getContextStageId(),
                outputProvider.getOutputIds()
        );
    }
}
