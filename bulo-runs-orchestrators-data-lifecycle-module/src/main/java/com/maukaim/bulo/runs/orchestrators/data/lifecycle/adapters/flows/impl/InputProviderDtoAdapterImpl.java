package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.InputProviderDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.InputProviderDto;

public class InputProviderDtoAdapterImpl implements InputProviderDtoAdapter {
    @Override
    public InputProviderDto adapte(InputProvider inputProvider) {
        return new InputProviderDto(
                inputProvider.getFlowStageId(),
                inputProvider.getOutputIds());
    }
}
