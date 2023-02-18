package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.io.definitions.client.dtos.functional.InputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;

public interface InputProviderDtoAdapter {
    InputProviderDto adapte(InputProvider inputProvider);
}
