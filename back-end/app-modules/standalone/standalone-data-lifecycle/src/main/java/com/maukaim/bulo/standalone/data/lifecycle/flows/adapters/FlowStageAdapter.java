package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters;

import com.maukaim.bulo.flows.data.models.flow.FlowStage;

public interface FlowStageAdapter {
    com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage adapte(FlowStage flowStage);
}
