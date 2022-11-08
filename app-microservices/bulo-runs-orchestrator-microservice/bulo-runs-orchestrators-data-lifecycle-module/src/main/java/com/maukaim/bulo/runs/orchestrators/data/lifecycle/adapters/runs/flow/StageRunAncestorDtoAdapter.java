package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.StageRunAncestorDto;

public interface StageRunAncestorDtoAdapter {
    StageRunAncestorDto adapte(StageRunAncestor stageRunAncestor);
}
