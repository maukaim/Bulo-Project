package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import java.util.Set;

public enum StageRunStatus {
    TO_BE_REQUESTED("Stage run to be requested soon."),
    REQUESTED("Stage run requested."),
    ACKNOWLEDGED("Stage Run acknowledged by a StageExecutor. In Process to be run."),
    RUNNING("StageExecutor started to run."),
    TO_BE_CANCELLED("Stage cancel requested."),
    CANCELLED("Stage cancelled."),
    FAILED("Stage run is failed"),
    SUCCESS("Stage run is successful.");

    private final String description;

    StageRunStatus(String description) {
        this.description = description;
    }

    public boolean isTerminal() {
        return Set.of(CANCELLED, FAILED, SUCCESS).contains(this);
    }

    public StageRunStatus resolveComparedTo(StageRunStatus other){
        return other != null && other.isTerminal() ? other : this;
    }

    public String getDescription() {
        return description;
    }
}
