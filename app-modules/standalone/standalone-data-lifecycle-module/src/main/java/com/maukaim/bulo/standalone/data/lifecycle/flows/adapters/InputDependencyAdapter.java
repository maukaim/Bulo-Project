package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters;

import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;

public interface InputDependencyAdapter {
    InputDependency adapte(IoDependency ioDependency);
}
