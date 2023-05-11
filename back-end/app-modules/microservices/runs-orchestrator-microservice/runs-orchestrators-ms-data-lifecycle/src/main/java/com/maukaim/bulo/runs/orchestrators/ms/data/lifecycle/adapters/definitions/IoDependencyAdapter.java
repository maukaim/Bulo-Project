package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.io.definitions.client.dtos.functional.IoDependencyDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;

public interface IoDependencyAdapter {
    IoDependency adapte(IoDependencyDto dto);
}
