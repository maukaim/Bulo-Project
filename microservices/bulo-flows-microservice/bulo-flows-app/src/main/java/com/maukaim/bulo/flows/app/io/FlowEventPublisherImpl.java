package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.flows.io.FlowEventPublisher;
import com.maukaim.bulo.flows.io.events.FlowEvent;

public class FlowEventPublisherImpl implements FlowEventPublisher {
    @Override
    public boolean publish(FlowEvent flowEvent) {
        System.out.println("Fake publish to KAFKA ! ::: " + flowEvent);
        return false; // Say it was not really published. Activate data safe mechanism
    }
}
