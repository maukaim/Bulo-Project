package com.maukaim.bulo.flows.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.io.flow.FlowStageDto;

public interface FlowStageDtoAdapter {
    FlowStageDto adapte(FlowStage flowStage);
}
