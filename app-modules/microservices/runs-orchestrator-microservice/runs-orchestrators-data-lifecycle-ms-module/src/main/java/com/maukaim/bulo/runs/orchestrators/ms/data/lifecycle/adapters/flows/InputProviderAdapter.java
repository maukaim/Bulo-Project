package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider;
import com.maukaim.bulo.io.flows.client.model.InputProviderDto;

public interface InputProviderAdapter {
    InputProvider adapte(InputProviderDto dto);
}
