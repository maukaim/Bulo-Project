package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.io.flows.client.model.FlowStageDto;

public interface FlowStageAdapter {
    FlowStage adapte(FlowStageDto dto);
}
