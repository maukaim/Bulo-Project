package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;
import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.IoDependencyDto;

public interface IoDependencyAdapter {
    IoDependency adapte(IoDependencyDto dto);
}
