package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;

public interface NeedStageRunCancellationEventPublisher {
    boolean publish(NeedStageRunCancellationEvent event);
}
