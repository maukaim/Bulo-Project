package com.maukaim.bulo.definitions.data.lifecycle.adapters.functional;

import com.maukaim.bulo.commons.io.instructions.models.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.functional.IoDependency;

public interface IoDependencyAdapter {
    IoDependency adapte(IoDependencyDto dto);
}
