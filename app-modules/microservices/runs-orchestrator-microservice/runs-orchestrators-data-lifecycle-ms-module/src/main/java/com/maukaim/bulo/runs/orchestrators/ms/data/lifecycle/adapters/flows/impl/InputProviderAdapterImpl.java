package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.InputProviderAdapter;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.InputProviderDto;

public class InputProviderAdapterImpl implements InputProviderAdapter {
    @Override
    public InputProvider adapte(InputProviderDto dto) {
        return new InputProvider(
                dto.getFlowStageId(),
                dto.getOutputIds()
        );
    }
}
