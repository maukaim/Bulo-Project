package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextStageAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.StageRunAncestorDto;

public interface StageRunAncestorAdapter {
    ContextStageAncestor adapte(StageRunAncestorDto dto);
}
