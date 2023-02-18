package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.InputProviderDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.io.flows.flow.InputProviderDto;

public class InputProviderDtoAdapterImpl implements InputProviderDtoAdapter {
    @Override
    public InputProviderDto adapte(InputProvider inputProvider) {
        return new InputProviderDto(
                inputProvider.getFlowStageId(),
                inputProvider.getOutputIds()
        );
    }
}
