package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions;


import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.InputProviderDto;

public interface InputProviderAdapter {
    InputProvider adapte(InputProviderDto dto);
}
