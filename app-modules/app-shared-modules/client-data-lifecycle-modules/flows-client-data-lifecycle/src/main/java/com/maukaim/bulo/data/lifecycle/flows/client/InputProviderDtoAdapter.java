package com.maukaim.bulo.data.lifecycle.flows.client;

import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.io.flows.client.model.InputProviderDto;

public interface InputProviderDtoAdapter {
    InputProviderDto adapte(InputProvider inputProvider);
}
