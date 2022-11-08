package com.maukaim.bulo.standalone.data.lifecycle.runs.adapters;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;

public interface StageRunDependencyAdapter {
    com.maukaim.bulo.executors.data.runs.StageRunDependency adapte(RunDependency runDependency);
}
