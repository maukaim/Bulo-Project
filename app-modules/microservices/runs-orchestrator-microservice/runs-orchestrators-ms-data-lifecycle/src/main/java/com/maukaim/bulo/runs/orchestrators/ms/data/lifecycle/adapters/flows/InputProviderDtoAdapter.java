package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.io.flows.client.model.InputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider;

public interface InputProviderDtoAdapter {
    InputProviderDto adapte(InputProvider inputProvider);
}
