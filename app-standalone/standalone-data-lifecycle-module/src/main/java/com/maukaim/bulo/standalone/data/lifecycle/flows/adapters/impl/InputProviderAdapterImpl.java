package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.impl;

import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.InputProviderAdapter;

public class InputProviderAdapterImpl implements InputProviderAdapter {
    public com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider adapte(InputProvider inputProvider){
        return new com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider(
                inputProvider.getFlowStageId(),
                inputProvider.getOutputIds()
        );
    }
}
