package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.FlowAdapter;
import com.maukaim.bulo.io.runs.orchestrators.FlowEventConsumer;
import com.maukaim.bulo.io.runs.orchestrators.events.FlowEvent;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.FlowDto;

public class FlowEventConsumerImpl implements FlowEventConsumer {
    private final FlowAdapter flowAdapter;
    private final FlowStore flowStore;

    public FlowEventConsumerImpl(FlowAdapter flowAdapter, FlowStore flowStore) {
        this.flowAdapter = flowAdapter;
        this.flowStore = flowStore;
    }

    @Override
    public void onFlowEvent(FlowEvent flowEvent) {
        System.out.println("Consume event: " + flowEvent);
        switch (flowEvent.getFlowEventType()) {
            case UPDATED -> this.update(flowEvent.getFlow());
            case ARCHIVED -> this.remove(flowEvent.getFlow().getFlowId());
        }
    }

    private void update(FlowDto dto) {
        Flow flow = this.flowAdapter.adapte(dto);
        this.flowStore.put(flow);
    }

    private void remove(String flowId) {
        this.flowStore.remove(flowId);
    }
}
