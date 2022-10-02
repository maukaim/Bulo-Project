package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters;

import com.maukaim.bulo.flows.data.models.flow.InputProvider;

public interface InputProviderAdapter {
    com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider adapte(InputProvider inputProvider);
}
