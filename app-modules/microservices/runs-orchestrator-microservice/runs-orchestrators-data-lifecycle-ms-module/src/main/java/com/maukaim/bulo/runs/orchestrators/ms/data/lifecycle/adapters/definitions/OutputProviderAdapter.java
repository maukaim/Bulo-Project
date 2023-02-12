package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.commons.io.instructions.models.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.InputProvider;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.InputProviderDto;

public interface OutputProviderAdapter {
    OutputProvider adapte(OutputProviderDto dto);
}
