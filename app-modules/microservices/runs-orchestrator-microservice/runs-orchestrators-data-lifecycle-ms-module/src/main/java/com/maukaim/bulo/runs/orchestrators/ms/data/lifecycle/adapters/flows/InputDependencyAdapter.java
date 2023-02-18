package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.InputDependencyDto;

public interface InputDependencyAdapter {
    InputDependency adapte(InputDependencyDto dto);
}
