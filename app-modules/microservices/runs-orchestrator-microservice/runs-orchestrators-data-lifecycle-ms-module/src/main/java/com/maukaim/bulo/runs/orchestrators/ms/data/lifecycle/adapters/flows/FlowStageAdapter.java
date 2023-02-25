package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.io.flows.client.model.FlowStageDto;
import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;

public interface FlowStageAdapter {
    FlowStage adapte(FlowStageDto dto);
}
