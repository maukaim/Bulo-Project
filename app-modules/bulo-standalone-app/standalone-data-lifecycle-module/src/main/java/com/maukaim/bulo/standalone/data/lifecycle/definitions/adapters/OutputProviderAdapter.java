package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters;

import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;

public interface OutputProviderAdapter {
    com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider adapteOrchestratorModule(OutputProvider outputProvider);
}
