package com.maukaim.bulo.runs.orchestrators.app;

import com.maukaim.bulo.app.shared.spring.servers.StandardSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.maukaim.bulo.app.shared.system.communication.api.ServiceName.BULO_MS_ORCHESTRATOR_SERVICE;

@SpringBootApplication
public class RunsOrchestratorApplication extends StandardSpringApplication {

    public static void main(String[] args) {
        launchSpringApp(BULO_MS_ORCHESTRATOR_SERVICE, RunsOrchestratorApplication.class, args);
    }
}
