package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.FlowRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowRunEventConsumer;
import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunEvent;

public class FlowRunEventConsumerImpl implements FlowRunEventConsumer {
    private final FlowRunStoreImpl flowRunStore;
    private final FlowRunAdapter flowRunAdapter;

    public FlowRunEventConsumerImpl(FlowRunStoreImpl flowRunStore,
                                    FlowRunAdapter flowRunAdapter) {
        this.flowRunStore = flowRunStore;
        this.flowRunAdapter = flowRunAdapter;
    }

    @Override
    public void consume(FlowRunEvent flowRunEvent) {
        System.out.println("Consume event: " + flowRunEvent);
        FlowRun flowRunToSave = this.flowRunAdapter.adapte(flowRunEvent.getFlowRunDto());
        this.flowRunStore.save(flowRunToSave);
    }
}
