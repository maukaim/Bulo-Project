package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.InputProviderAdapter;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.io.flows.flow.InputProviderDto;

public class InputProviderAdapterImpl implements InputProviderAdapter {
    @Override
    public InputProvider adapte(InputProviderDto dto) {
        return new InputProvider(
                dto.getFlowStageId(),
                dto.getOutputIds()
        );
    }
}
