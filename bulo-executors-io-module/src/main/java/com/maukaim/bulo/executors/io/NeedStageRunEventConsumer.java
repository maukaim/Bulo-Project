package com.maukaim.bulo.executors.io;

import com.maukaim.bulo.executors.io.in.NeedStageRunEvent;

public interface NeedStageRunEventConsumer {
    void consume(NeedStageRunEvent event);
}
