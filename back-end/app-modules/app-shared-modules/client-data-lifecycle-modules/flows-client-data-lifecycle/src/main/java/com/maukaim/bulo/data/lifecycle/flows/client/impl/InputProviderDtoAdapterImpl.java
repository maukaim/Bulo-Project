package com.maukaim.bulo.data.lifecycle.flows.client.impl;

import com.maukaim.bulo.data.lifecycle.flows.client.InputProviderDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.io.flows.client.model.InputProviderDto;

public class InputProviderDtoAdapterImpl implements InputProviderDtoAdapter {
    @Override
    public InputProviderDto adapte(InputProvider inputProvider) {
        return new InputProviderDto(
                inputProvider.getFlowStageId(),
                inputProvider.getOutputIds()
        );
    }
}
