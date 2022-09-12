package com.maukaim.bulo.flows.data.lifecycle.adapters.impl;

import com.maukaim.bulo.flows.data.lifecycle.adapters.InputProviderDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.io.flow.InputProviderDto;

public class InputProviderDtoAdapterImpl implements InputProviderDtoAdapter {
    @Override
    public InputProviderDto adapte(InputProvider inputProvider) {
        return new InputProviderDto(
                inputProvider.getFlowStageId(),
                inputProvider.getOutputIds()
        );
    }
}
