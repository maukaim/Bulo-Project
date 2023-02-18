package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.NeedStageRunCancellationEvent;

public interface NeedStageRunCancellationEventPublisher {
    boolean publish(NeedStageRunCancellationEvent event);
}
