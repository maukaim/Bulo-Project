package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters;

import com.maukaim.bulo.runs.orchestrators.data.definition.FsStage;

public interface FsStageAdapter {
    FsStage adapteOrchestratorModule(com.maukaim.bulo.definitions.data.definition.functional.FsStage fsStage);
}
