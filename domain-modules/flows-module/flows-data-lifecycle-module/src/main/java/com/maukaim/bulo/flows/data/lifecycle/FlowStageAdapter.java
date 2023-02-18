package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.io.flows.system.flow.FlowStageDto;

public interface FlowStageAdapter {
    FlowStage adapte(FlowStageDto dto);
}
