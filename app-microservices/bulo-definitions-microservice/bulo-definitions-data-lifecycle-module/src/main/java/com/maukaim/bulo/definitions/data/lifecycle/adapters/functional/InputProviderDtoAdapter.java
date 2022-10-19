package com.maukaim.bulo.definitions.data.lifecycle.adapters.functional;

import com.maukaim.bulo.commons.io.instructions.models.functional.InputProviderDto;
import com.maukaim.bulo.definitions.data.functional.InputProvider;

public interface InputProviderAdapter {
    InputProvider adapte(InputProviderDto dto);
}
