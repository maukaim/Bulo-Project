package com.maukaim.bulo.runs.orchestrators.io;

import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunExecutionEvent;

public interface NeedStageRunExecutionEventPublisher {
    boolean publish(NeedStageRunExecutionEvent needStageRunExecutionEvent);

}
