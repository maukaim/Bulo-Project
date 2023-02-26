package com.maukaim.bulo.data.lifecycle.flows.client.impl;

import com.maukaim.bulo.data.lifecycle.flows.client.InputProviderAdapter;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.io.flows.client.model.InputProviderDto;

public class InputProviderAdapterImpl implements InputProviderAdapter {
    @Override
    public InputProvider adapte(InputProviderDto dto) {
        return new InputProvider(
                dto.getFlowStageId(),
                dto.getOutputIds()
        );
    }
}
