package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.commons.io.instructions.models.functional.OutputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;

public interface OutputProviderAdapter {
    OutputProvider adapte(OutputProviderDto dto);
}
