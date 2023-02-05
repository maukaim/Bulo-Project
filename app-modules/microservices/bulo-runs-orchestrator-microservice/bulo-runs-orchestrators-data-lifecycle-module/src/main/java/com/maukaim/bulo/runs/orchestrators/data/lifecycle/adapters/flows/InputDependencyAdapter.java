package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.InputDependencyDto;

public interface InputDependencyAdapter {
    InputDependency adapte(InputDependencyDto dto);
}
