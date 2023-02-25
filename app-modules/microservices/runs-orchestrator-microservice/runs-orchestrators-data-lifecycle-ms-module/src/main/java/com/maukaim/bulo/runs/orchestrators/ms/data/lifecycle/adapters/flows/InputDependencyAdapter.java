package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.io.flows.client.model.InputDependencyDto;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;

public interface InputDependencyAdapter {
    InputDependency adapte(InputDependencyDto dto);
}
