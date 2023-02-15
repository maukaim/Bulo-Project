package com.maukaim.bulo.definitions.data.lifecycle.functional.impl;

import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.OutputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;
import com.maukaim.bulo.definitions.data.lifecycle.functional.OutputProviderDtoAdapter;

public class OutputProviderDtoAdapterImpl implements OutputProviderDtoAdapter {
    @Override
    public OutputProviderDto adapte(OutputProvider outputProvider) {
        return new OutputProviderDto(
                outputProvider.getContextStageId(),
                outputProvider.getOutputIds()
        );
    }
}
