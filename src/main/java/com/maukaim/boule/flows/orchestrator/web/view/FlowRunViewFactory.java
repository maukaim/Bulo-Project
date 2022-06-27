package com.maukaim.boule.flows.orchestrator.web.view;

import com.maukaim.boule.flows.orchestrator.run.FlowRun;

public class FlowRunViewFactory {

    public static FlowRunView build(FlowRun run){
        return new FlowRunView(run.getFlowRunId(),
                run.getFlowId(),
                run.getFlowRunStatus(),
                run.getRunningStages());
    }
}
