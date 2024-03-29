package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.TriggerEventConsumer;
import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunStartEvent;

import java.util.Set;

public class TriggerEventConsumerImpl implements TriggerEventConsumer {
    private final FlowRunService flowRunService;

    public TriggerEventConsumerImpl(FlowRunService flowRunService){
        this.flowRunService = flowRunService;
    }
    @Override
    public String onFlowRunInstruction(FlowRunStartEvent triggerEvent) {
        System.out.println("Consume event: " + triggerEvent);
        Set<ContextStageId> contextStageIds = triggerEvent.getFlowStageIds();
        FlowRun flowRun = this.flowRunService.startRun(triggerEvent.getFlowId(), contextStageIds);
        return flowRun != null? flowRun.getContextId() : null;
    }
}
