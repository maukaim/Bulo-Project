package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.in.RunInstruction;

public interface NeedStageRunEventConsumer {
    void consume(RunInstruction event);
}
