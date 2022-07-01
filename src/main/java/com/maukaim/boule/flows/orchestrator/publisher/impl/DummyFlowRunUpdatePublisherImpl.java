package com.maukaim.boule.flows.orchestrator.publisher.impl;

import com.maukaim.boule.flows.orchestrator.publisher.FlowRunUpdatePublisher;
import com.maukaim.boule.flows.orchestrator.publisher.util.DummyMessageUtil;
import com.maukaim.boule.flows.orchestrator.flow.run.FlowRun;

public class DummyFlowRunUpdatePublisherImpl implements FlowRunUpdatePublisher {
    @Override
    public void publishUpdate(FlowRun flowRun) {
        System.out.printf("%s new version of FlowRun %s%n",
                DummyMessageUtil.MESSAGE_PREFIX,
                flowRun);
    }
}
