package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters;

import com.maukaim.bulo.runs.orchestrators.data.definition.IoDependency;

public interface IoDependencyAdapter {
    IoDependency adapteOrchestratorModule(com.maukaim.bulo.definitions.data.definition.functional.IoDependency ioDependency);
}
