package com.maukaim.bulo.app.shared.system.communication.api;

import static com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode.microservices;
import static com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode.standalone;

public enum ServiceName {
    STANDALONE(standalone),
    BULO_MS_DEFINITIONS_SERVICE(microservices),
    BULO_MS_EXECUTORS_SERVICE(microservices),
    BULO_MS_FLOWS_SERVICE(microservices),
    BULO_MS_ORCHESTRATOR_SERVICE(microservices),
    BULO_MS_STAGES_SERVICE(microservices),
    BULO_MS_TRIGGER_SCHEDULER_SERVICE(microservices);

    private final ApplicationMode applicationMode;

    ServiceName(ApplicationMode applicationMode) {
        this.applicationMode = applicationMode;
    }

    public ApplicationMode getApplicationMode() {
        return applicationMode;
    }
}
