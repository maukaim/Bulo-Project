package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;

public interface NeedStageRunCancelEventConsumer {
    void consume(NeedStageRunCancellationEvent event);
}
