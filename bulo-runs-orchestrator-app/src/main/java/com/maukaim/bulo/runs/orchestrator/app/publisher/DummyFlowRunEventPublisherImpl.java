package com.maukaim.bulo.runs.orchestrator.app.publisher;

import com.maukaim.bulo.runs.orchestrator.core.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.model.FlowRun;

public class DummyFlowRunEventPublisherImpl implements FlowRunEventPublisher {
    @Override
    public void publishUpdate(FlowRun flowRun) {
        System.out.printf("%s new version of FlowRun %s%n",
                DummyMessageUtil.MESSAGE_PREFIX,
                flowRun);
    }
}
