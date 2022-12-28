package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.InputProvider;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.InputProviderDto;

public interface InputProviderDtoAdapter {
    InputProviderDto adapte(InputProvider inputProvider);
}
