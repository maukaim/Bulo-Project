package com.maukaim.bulo.executor.app.io;

import com.maukaim.bulo.executor.io.TsdDeclarationEventPublisher;
import com.maukaim.bulo.executor.io.out.TsdDeclarationEvent;

public class DummyTsdDeclarationEventPublisher implements TsdDeclarationEventPublisher {
    @Override
    public boolean publish(TsdDeclarationEvent event) {
        System.out.println("Fake publish to KAFKA ! ::: " + event);
        return true;
    }
}
