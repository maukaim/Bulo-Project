package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.executors.data.lifecycle.StageRunResultStoreImpl;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunResultAdapter;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.io.StageRunResultEventConsumer;
import com.maukaim.bulo.executors.io.out.StageRunResultEvent;

public class StageRunResultEventConsumerImpl implements StageRunResultEventConsumer {
    private final StageRunResultStoreImpl stageRunResultStore;
    private final StageRunResultAdapter stageRunResultAdapter;

    public StageRunResultEventConsumerImpl(StageRunResultStoreImpl stageRunResultStore, StageRunResultAdapter stageRunResultAdapter) {
        this.stageRunResultStore = stageRunResultStore;
        this.stageRunResultAdapter = stageRunResultAdapter;
    }

    @Override
    public void onStageRunResultEvent(StageRunResultEvent event) {
        System.out.println("Consume event: " + event);
        StageRunResult stageRunResult = this.stageRunResultAdapter.adapte(event.getStageRunResult());
        this.stageRunResultStore.save(stageRunResult);
    }
}
