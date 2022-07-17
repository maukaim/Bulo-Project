package com.maukaim.bulo.common.io;

public enum StageRunEventType {
    ACKNOWLEDGE_REQUEST("Acknowledge the request, the executor will process it soon."),
    LAUNCH_RUN("Executor started to run the stage."),
    RUN_CANCELLED("Run was cancelled"),
    RUN_FAILED("Stage run is failed"),
    RUN_SUCCESSFUL("Stage run is successful.");

    private final String description;

    StageRunEventType(String description) {
        this.description = description;
    }
}
