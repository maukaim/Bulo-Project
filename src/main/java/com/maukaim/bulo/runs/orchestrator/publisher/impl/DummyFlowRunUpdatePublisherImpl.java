package com.maukaim.bulo.runs.orchestrator.publisher.impl;

import com.maukaim.bulo.runs.orchestrator.publisher.FlowRunUpdatePublisher;
import com.maukaim.bulo.runs.orchestrator.publisher.util.DummyMessageUtil;
import com.maukaim.bulo.runs.orchestrator.flow.run.FlowRun;

public class DummyFlowRunUpdatePublisherImpl implements FlowRunUpdatePublisher {
    @Override
    public void publishUpdate(FlowRun flowRun) {
        System.out.printf("%s new version of FlowRun %s%n",
                DummyMessageUtil.MESSAGE_PREFIX,
                flowRun);
    }
}
