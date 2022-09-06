package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageAncestorDto;

public interface FlowStageAncestorDtoAdapter {
    FlowStageAncestorDto adapte(FlowStageAncestor flowStageAncestor);
}
