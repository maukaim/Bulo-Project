package com.maukaim.bulo.runs.orchestrators.io;

import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunCancellationEvent;

public interface NeedStageRunCancellationEventPublisher {
    boolean publish(NeedStageRunCancellationEvent event);
}
