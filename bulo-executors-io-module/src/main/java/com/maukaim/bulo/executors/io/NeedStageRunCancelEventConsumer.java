package com.maukaim.bulo.executors.io;

import com.maukaim.bulo.executors.io.in.NeedStageRunCancelEvent;

public interface NeedStageRunCancelEventConsumer {
    void consume(NeedStageRunCancelEvent event);
}
