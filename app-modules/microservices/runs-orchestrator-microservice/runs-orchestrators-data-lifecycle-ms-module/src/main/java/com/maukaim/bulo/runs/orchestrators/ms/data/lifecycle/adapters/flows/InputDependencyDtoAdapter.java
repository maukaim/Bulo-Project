package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.InputDependencyDto;

public interface InputDependencyDtoAdapter {
    InputDependencyDto adapte(InputDependency inputDependency);
}
