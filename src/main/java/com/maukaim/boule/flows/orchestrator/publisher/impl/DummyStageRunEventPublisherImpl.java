package com.maukaim.boule.flows.orchestrator.publisher.impl;

import com.maukaim.boule.flows.orchestrator.publisher.StageRunEventPublisher;
import com.maukaim.boule.flows.orchestrator.publisher.util.DummyMessageUtil;

import java.util.Set;

public class DummyStageRunEventPublisherImpl implements StageRunEventPublisher {

    @Override
    public boolean requestAsyncRun(String flowRunId, Set<String> stageIds) {
        stageIds.forEach(stageId ->
                System.out.printf("%s Please run stage [%s] on behalf of flowRun [%s]%n",
                        DummyMessageUtil.MESSAGE_PREFIX,
                        stageId,
                        flowRunId));
        return true;
    }

    @Override
    public boolean requestSyncCancel(String flowRunId, String stageId) {
        return false;
    }
}
