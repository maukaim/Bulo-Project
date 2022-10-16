package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.runs.StageRunDependency;
import com.maukaim.bulo.executors.io.in.model.StageRunDependencyDto;

public interface StageRunDependencyAdapter {
    StageRunDependency adapte(StageRunDependencyDto dto);
}
