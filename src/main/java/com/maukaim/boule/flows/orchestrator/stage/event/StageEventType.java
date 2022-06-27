package com.maukaim.boule.flows.orchestrator.stage.event;

public enum StageEventType {
    ACKNOWLEDGE_REQUEST("Acknowledge the request, the executor will process it soon."),
    LAUNCH_RUN("Executor started to run the stage."),
    RUN_CANCELLED("Run was cancelled"),
    RUN_FAILED("Stage run is failed"),
    RUN_SUCCESSFUL("Stage run is successful.");

    private final String description;

    StageEventType(String description) {
        this.description = description;
    }
}
