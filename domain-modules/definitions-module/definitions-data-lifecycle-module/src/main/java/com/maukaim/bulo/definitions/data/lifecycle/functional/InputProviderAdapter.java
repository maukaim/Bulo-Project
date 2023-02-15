package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.InputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;

public interface InputProviderAdapter {
    InputProvider adapte(InputProviderDto dto);
}
