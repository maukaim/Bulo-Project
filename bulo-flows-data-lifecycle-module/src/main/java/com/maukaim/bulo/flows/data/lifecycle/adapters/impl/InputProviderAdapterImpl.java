package com.maukaim.bulo.flows.data.lifecycle.adapters.impl;

import com.maukaim.bulo.flows.data.lifecycle.adapters.InputProviderAdapter;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.io.flow.InputProviderDto;

public class InputProviderAdapterImpl implements InputProviderAdapter {
    @Override
    public InputProvider adapte(InputProviderDto dto) {
        return new InputProvider(
                dto.getFlowStageId(),
                dto.getOutputIds()
        );
    }
}
