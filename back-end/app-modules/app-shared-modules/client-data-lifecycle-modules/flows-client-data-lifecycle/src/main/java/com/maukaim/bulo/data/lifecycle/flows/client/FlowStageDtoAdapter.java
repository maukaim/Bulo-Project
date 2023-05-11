package com.maukaim.bulo.data.lifecycle.flows.client;

import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.io.flows.client.model.FlowStageDto;

public interface FlowStageDtoAdapter {
    FlowStageDto adapte(FlowStage flowStage);
}
