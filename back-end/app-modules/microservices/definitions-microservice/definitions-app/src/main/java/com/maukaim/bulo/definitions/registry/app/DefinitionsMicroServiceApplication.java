package com.maukaim.bulo.definitions.registry.app;

import com.maukaim.bulo.app.shared.spring.servers.StandardSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.maukaim.bulo.app.shared.system.communication.api.ServiceName.BULO_MS_DEFINITIONS_SERVICE;

@SpringBootApplication
public class DefinitionsMicroServiceApplication extends StandardSpringApplication {

    public static void main(String[] args) {
        launchSpringApp(BULO_MS_DEFINITIONS_SERVICE, DefinitionsMicroServiceApplication.class, args);
    }
}
