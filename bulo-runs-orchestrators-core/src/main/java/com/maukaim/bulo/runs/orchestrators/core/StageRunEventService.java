package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.commons.io.IStageRunEvent;

public interface StageRunEventService {
    void process(IStageRunEvent event);
}
