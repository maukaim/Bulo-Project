package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.runs.orchestrators.app.io.DummyPublisherAbstract;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunCancellationEvent;

public class DummyNeedStageRunCancellationEventPublisherImpl extends DummyPublisherAbstract<NeedStageRunCancellationEvent>
        implements NeedStageRunCancellationEventPublisher {
}
