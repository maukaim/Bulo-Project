package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.commons.io.instructions.models.functional.InputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;

public interface InputProviderDtoAdapter {
    InputProviderDto adapte(InputProvider inputProvider);
}
