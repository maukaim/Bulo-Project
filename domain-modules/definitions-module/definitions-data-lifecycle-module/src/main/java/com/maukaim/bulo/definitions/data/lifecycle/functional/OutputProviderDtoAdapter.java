package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;

public interface OutputProviderDtoAdapter {
    OutputProviderDto adapte(OutputProvider outputProvider);
}
