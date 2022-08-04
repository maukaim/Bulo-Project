package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.io.StageUpdateEvent;
import com.maukaim.bulo.io.StageUpdateEventConsumer;
import com.maukaim.bulo.io.StageUpdateEventPublisher;

/**
 * Just for testing purpose. Otherwise, pattern make no sense.
 */
public class DirectStageUpdateEventPublisherImpl implements StageUpdateEventPublisher {
    private StageUpdateEventConsumer consumer;

    public DirectStageUpdateEventPublisherImpl() {
    }

    public void setConsumer(StageUpdateEventConsumer consumer){
        this.consumer = consumer;
    }

    @Override
    public boolean publish(StageUpdateEvent event) {
        System.out.println("Direct publish to Consumer new StageUpdate:: " + event);
        this.consumer.consume(event);
        return true;
    }
}
