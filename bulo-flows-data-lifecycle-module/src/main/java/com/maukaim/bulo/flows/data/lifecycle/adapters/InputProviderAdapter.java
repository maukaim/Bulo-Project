package com.maukaim.bulo.flows.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.io.flow.InputProviderDto;

public interface InputProviderAdapter {
    InputProvider adapte(InputProviderDto dto);
}
