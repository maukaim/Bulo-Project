package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.flows.ms.data.lifecycle.FlowStoreImpl;
import com.maukaim.bulo.flows.data.lifecycle.FlowAdapter;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.io.flows.system.FlowEventConsumer;
import com.maukaim.bulo.io.flows.system.events.FlowEvent;
import com.maukaim.bulo.io.flows.system.flow.FlowDto;

public class FlowEventConsumerImpl implements FlowEventConsumer {
    private final FlowStoreImpl flowStore;
    private final FlowAdapter flowAdapter;

    public FlowEventConsumerImpl(FlowStoreImpl flowStore, FlowAdapter flowAdapter) {
        this.flowStore = flowStore;
        this.flowAdapter = flowAdapter;
    }

    @Override
    public void onFlowEvent(FlowEvent event) {
        System.out.println("Consume event: " + event);

        switch (event.getFlowEventType()) {
            case UPDATED -> save(event.getFlow());
            case ARCHIVED -> remove(event.getFlow());
        }
    }

    private void remove(FlowDto flowDto) {
        if(flowDto != null && flowDto.getFlowId() != null){
            this.flowStore.delete(flowDto.getFlowId());
        }
    }

    private void save(FlowDto flowDto) {
        Flow flowToSave = this.flowAdapter.adapte(flowDto);
        this.flowStore.save(flowToSave);
    }
}
