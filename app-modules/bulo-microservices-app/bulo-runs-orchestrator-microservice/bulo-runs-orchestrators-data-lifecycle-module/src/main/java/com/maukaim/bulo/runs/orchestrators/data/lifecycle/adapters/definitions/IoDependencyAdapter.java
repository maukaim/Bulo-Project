package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions;


import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.IoDependencyDto;

public interface IoDependencyAdapter {
    IoDependency adapte(IoDependencyDto dto);
}
