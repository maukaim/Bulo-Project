package com.maukaim.bulo.executor.io;

import com.maukaim.bulo.executor.io.out.TsdDeclarationEvent;

public interface TsdDeclarationEventPublisher {
    boolean publish(TsdDeclarationEvent event);
}
