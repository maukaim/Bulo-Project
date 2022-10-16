package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.flows.io.flow.InputProviderDto;
import com.maukaim.bulo.flows.io.flow.IoDependencyDto;

public interface InputProviderDtoAdapter {
    InputProviderDto adapte(InputProvider inputProvider);
}
