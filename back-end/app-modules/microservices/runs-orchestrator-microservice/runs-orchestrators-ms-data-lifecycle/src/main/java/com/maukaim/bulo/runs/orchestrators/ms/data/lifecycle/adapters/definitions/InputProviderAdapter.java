package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.io.definitions.client.dtos.functional.InputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;

public interface InputProviderAdapter {
    InputProvider adapte(InputProviderDto dto);
}
