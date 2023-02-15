package com.maukaim.bulo.definitions.data.lifecycle.functional.impl;

import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.InputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;
import com.maukaim.bulo.definitions.data.lifecycle.functional.InputProviderDtoAdapter;

public class InputProviderDtoAdapterImpl implements InputProviderDtoAdapter {
    @Override
    public InputProviderDto adapte(InputProvider inputProvider) {
        return new InputProviderDto(
                inputProvider.getFlowStageId(),
                inputProvider.getOutputIds()
        );
    }
}
