package com.maukaim.bulo.io.runs.orchestrators.system;

import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;

public interface NeedStageRunCancellationEventPublisher {
    boolean publish(NeedStageRunCancellationEvent event);
}
