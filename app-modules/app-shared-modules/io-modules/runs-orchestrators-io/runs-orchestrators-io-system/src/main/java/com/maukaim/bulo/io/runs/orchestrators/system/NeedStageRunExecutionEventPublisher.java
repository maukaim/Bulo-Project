package com.maukaim.bulo.io.runs.orchestrators.system;

import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunExecutionEvent;

public interface NeedStageRunExecutionEventPublisher {
    boolean publish(NeedStageRunExecutionEvent needStageRunExecutionEvent);

}
