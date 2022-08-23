package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.FlowRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.FlowRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRun;
import com.maukaim.bulo.runs.orchestrators.io.FlowRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.both.FlowRunEvent;

public class FlowRunEventConsumerImpl implements FlowRunEventConsumer {
    private final FlowRunStoreImpl flowRunStore;
    private final FlowRunAdapter flowRunAdapter;

    public FlowRunEventConsumerImpl(FlowRunStoreImpl flowRunStore,
                                    FlowRunAdapter flowRunAdapter) {
        this.flowRunStore = flowRunStore;
        this.flowRunAdapter = flowRunAdapter;
    }

    @Override
    public void onFlowRunEvent(FlowRunEvent flowRunEvent) {
        FlowRun flowRunToSave = this.flowRunAdapter.adapte(flowRunEvent.getFlowRunDto());
        this.flowRunStore.save(flowRunToSave);
    }
}
