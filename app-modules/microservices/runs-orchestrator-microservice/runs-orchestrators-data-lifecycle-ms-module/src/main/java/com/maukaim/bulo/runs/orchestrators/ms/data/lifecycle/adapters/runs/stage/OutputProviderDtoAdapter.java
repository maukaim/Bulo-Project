package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage;

import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;

public interface OutputProviderDtoAdapter {
    OutputProviderDto adapte(OutputProvider outputProvider);
}
