package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.NeedStageRunExecutionEvent;

public interface NeedStageRunExecutionEventPublisher {
    boolean publish(NeedStageRunExecutionEvent needStageRunExecutionEvent);

}
