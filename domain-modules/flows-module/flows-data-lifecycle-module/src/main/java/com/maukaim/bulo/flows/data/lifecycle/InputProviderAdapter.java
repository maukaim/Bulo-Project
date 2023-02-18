package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.io.flows.system.flow.InputProviderDto;

public interface InputProviderAdapter {
    InputProvider adapte(InputProviderDto dto);
}
