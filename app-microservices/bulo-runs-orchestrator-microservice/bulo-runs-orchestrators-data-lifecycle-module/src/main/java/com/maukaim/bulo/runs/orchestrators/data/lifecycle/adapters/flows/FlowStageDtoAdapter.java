package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowStageDto;

public interface FlowStageDtoAdapter {
    FlowStageDto adapte(FlowStage flowStage);
}
