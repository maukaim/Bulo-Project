package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;

public interface OutputProviderAdapter {
    OutputProvider adapte(OutputProviderDto dto);
}
