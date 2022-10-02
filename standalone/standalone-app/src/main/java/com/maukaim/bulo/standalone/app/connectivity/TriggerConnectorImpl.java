package com.maukaim.bulo.standalone.app.connectivity;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;

import java.util.Set;

public class TriggerConnectorImpl implements TriggerConnector {
    private final FlowRunService flowRunService;

    public TriggerConnectorImpl(FlowRunService flowRunService) {
        this.flowRunService = flowRunService;
    }

    @Override
    public void requestTrigger(String flowId, Set<FlowStageId> flowStageIds) {
        this.flowRunService.startRun(flowId, flowStageIds);
    }
}
