package com.maukaim.bulo.stages.app;

import com.maukaim.bulo.app.shared.spring.servers.StandardSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.maukaim.bulo.app.shared.system.communication.api.ServiceName.BULO_MS_STAGES_SERVICE;

@SpringBootApplication
public class StagesServiceApplication extends StandardSpringApplication {
    public static void main(String[] args) {
        launchSpringApp(BULO_MS_STAGES_SERVICE, StagesServiceApplication.class, args);
    }
}
