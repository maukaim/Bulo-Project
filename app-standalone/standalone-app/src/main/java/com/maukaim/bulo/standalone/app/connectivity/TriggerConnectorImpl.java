package com.maukaim.bulo.standalone.app.connectivity;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;

import java.util.Set;

public class TriggerConnectorImpl implements TriggerConnector {
    private final FlowRunService flowRunService;

    public TriggerConnectorImpl(FlowRunService flowRunService) {
        this.flowRunService = flowRunService;
    }

    @Override
    public void requestTrigger(String flowId, Set<ContextualizedStageId> contextualizedStageIds) {
        this.flowRunService.startRun(flowId, contextualizedStageIds);
    }
}
