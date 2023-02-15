package com.maukaim.bulo.io.executors;

import com.maukaim.bulo.io.executors.in.CancelRunInstruction;

public interface NeedStageRunCancelEventConsumer {
    void consume(CancelRunInstruction event);
}
