package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunExecutionEvent;

public interface NeedStageRunEventConsumer {
    void consume(NeedStageRunExecutionEvent event);
}
