package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextStageAncestor;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.StageRunAncestorDto;

public interface StageRunAncestorAdapter {
    ContextStageAncestor adapte(StageRunAncestorDto dto);
}
