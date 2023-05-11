package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunExecutionEvent;

public interface NeedStageRunExecutionEventPublisher {
    boolean publish(NeedStageRunExecutionEvent needStageRunExecutionEvent);

}
