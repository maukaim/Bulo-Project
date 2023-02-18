package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;

public interface OutputProviderAdapter {
    OutputProvider adapte(OutputProviderDto dto);
}
