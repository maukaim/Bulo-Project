package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl;

import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.InputProviderAdapter;

public class InputProviderAdapterImpl implements InputProviderAdapter {
    @Override
    public InputProvider adapteOrchestratorModule(com.maukaim.bulo.definitions.data.definition.functional.InputProvider inputProvider) {
        return new InputProvider(
                inputProvider.getFlowStageId(),
                inputProvider.getOutputIds()
        );
    }
}
