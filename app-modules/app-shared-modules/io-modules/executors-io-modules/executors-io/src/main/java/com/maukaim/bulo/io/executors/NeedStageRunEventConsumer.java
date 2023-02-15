package com.maukaim.bulo.io.executors;

import com.maukaim.bulo.io.executors.in.RunInstruction;

public interface NeedStageRunEventConsumer {
    void consume(RunInstruction event);
}
