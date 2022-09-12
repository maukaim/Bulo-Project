package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.runs.orchestrators.app.io.DummyPublisherAbstract;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunExecutionEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunExecutionEvent;

public class DummyNeedStageRunExecutionEventPublisherImpl extends DummyPublisherAbstract<NeedStageRunExecutionEvent>
        implements NeedStageRunExecutionEventPublisher {
}
