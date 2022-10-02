package com.maukaim.bulo.executors.io;

import com.maukaim.bulo.executors.io.in.CancelRunInstruction;

public interface NeedStageRunCancelEventConsumer {
    void consume(CancelRunInstruction event);
}
