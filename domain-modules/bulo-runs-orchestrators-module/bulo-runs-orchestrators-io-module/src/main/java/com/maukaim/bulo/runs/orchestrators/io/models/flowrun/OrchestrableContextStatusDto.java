package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

public enum OrchestrableContextStatusDto {
    NEW,
    PENDING_START,
    RUNNING,
    PAUSED,
    CANCELLED,
    FAILED,
    SUCCESS;
}
