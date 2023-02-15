package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;

public interface IoDependencyAdapter {
    IoDependency adapte(IoDependencyDto dto);
}
