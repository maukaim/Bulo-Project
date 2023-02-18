package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.FlowStageDto;

public interface FlowStageAdapter {
    FlowStage adapte(FlowStageDto dto);
}
