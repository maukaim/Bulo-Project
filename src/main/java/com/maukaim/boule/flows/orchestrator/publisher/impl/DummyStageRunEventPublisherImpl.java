package com.maukaim.boule.flows.orchestrator.publisher.impl;

import com.maukaim.boule.flows.orchestrator.publisher.StageRunEventPublisher;
import com.maukaim.boule.flows.orchestrator.publisher.util.DummyMessageUtil;

import java.util.Set;

public class DummyStageRunEventPublisherImpl implements StageRunEventPublisher {

    @Override
    public boolean requestAsyncRunExecution(String globalStageId, String stageRunId) {
        System.out.printf("%s Please run stage [%s], its runId is [%s]%n",
                DummyMessageUtil.MESSAGE_PREFIX,
                globalStageId,
                stageRunId);
        return true;
    }

    @Override
    public void requestAsyncRunCancellation(String stageRunId) {
        System.out.printf("%s Please CANCEL stageRun [%s]%n",
                DummyMessageUtil.MESSAGE_PREFIX,
                stageRunId);
    }

    @Override
    public boolean requestSyncRunCancellation(String stageRunId, String executorId) {
        System.out.printf("%s Please CANCEL stageRun [%s], sent directly to executor [%s]%n",
                DummyMessageUtil.MESSAGE_PREFIX,
                stageRunId,
                executorId);
        return false;
    }
}
