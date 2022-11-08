package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters;

import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;

public interface InputProviderAdapter {
    InputProvider adapteOrchestratorModule(com.maukaim.bulo.definitions.data.definition.functional.InputProvider inputProvider);
}
