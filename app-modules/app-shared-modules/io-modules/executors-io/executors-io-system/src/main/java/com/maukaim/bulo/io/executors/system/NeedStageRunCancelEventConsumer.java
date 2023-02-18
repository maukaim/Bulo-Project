package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.in.CancelRunInstruction;

public interface NeedStageRunCancelEventConsumer {
    void consume(CancelRunInstruction event);
}
