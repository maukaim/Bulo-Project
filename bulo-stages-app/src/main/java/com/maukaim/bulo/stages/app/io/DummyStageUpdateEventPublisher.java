package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.io.StageUpdateEvent;
import com.maukaim.bulo.io.StageUpdateEventPublisher;

/**
 * Just for testing purpose. Otherwise, pattern make no sense.
 */
public class DummyStageUpdateEventPublisher implements StageUpdateEventPublisher {
    @Override
    public boolean publish(StageUpdateEvent event) {
        System.out.println("Fake publish to KAFKA ! ::: " + event);
        return true;
    }
}
