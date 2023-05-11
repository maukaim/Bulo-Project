package com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun;

public enum OrchestrableContextStatusDto {
    NEW,
    PENDING_START,
    RUNNING,
    PAUSED,
    CANCELLED,
    FAILED,
    SUCCESS;
}
