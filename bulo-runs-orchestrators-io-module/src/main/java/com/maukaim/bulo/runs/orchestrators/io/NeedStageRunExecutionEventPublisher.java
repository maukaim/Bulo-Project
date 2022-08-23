package com.maukaim.bulo.runs.orchestrators.io;

import com.maukaim.bulo.runs.orchestrators.io.out.NeedStageRunExecutionEvent;

public interface NeedStageRunExecutionEventPublisher {
    boolean publish(NeedStageRunExecutionEvent needStageRunExecutionEvent);

}
