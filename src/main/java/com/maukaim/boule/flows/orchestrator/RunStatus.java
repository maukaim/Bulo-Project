package com.maukaim.boule.flows.orchestrator;

public enum RunStatus {
    NEW("New Run, no task has ran yet."),
    PENDING_START("First Stage run requested."),
    RUNNING("A Stage is in process. Waiting for execution to finish."),
    PAUSED("Flow execution paused. Need intervention."),
    CANCELLED("Flow execution interrupted during process."),
    FAILED("Flow execution terminated, one or more error(s) raised during the process."),
    SUCCESS("Flow execution terminated, everything ran as expected.");

    private final String description;

    RunStatus(String description) {
        this.description = description;
    }
}
