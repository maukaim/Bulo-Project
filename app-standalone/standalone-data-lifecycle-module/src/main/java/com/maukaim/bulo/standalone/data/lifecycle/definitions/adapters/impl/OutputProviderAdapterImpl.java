package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl;

import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.OutputProviderAdapter;

public class OutputProviderAdapterImpl implements OutputProviderAdapter {
    @Override
    public OutputProvider adapteOrchestratorModule(com.maukaim.bulo.definitions.data.definition.functional.OutputProvider outputProvider) {
        return new OutputProvider(outputProvider.getContextStageId(),
                outputProvider.getOutputIds());
    }
}
