package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.commons.io.instructions.models.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.OutputProviderDtoAdapter;

public class OutputProviderDtoAdapterImpl implements OutputProviderDtoAdapter {
    @Override
    public OutputProviderDto adapte(OutputProvider outputProvider) {
        return new OutputProviderDto(
                outputProvider.getContextStageId(),
                outputProvider.getOutputIds()
        );
    }
}
