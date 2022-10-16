package com.maukaim.bulo.executors.io;

import com.maukaim.bulo.executors.io.in.RunInstruction;

public interface NeedStageRunEventConsumer {
    void consume(RunInstruction event);
}
