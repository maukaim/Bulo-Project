package com.maukaim.bulo.flows.app;

import com.maukaim.bulo.app.shared.spring.servers.StandardSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.maukaim.bulo.app.shared.system.communication.api.ServiceName.BULO_MS_FLOWS_SERVICE;

@SpringBootApplication
public class FlowServiceApplication extends StandardSpringApplication {

    public static void main(String[] args) {
        launchSpringApp(BULO_MS_FLOWS_SERVICE, FlowServiceApplication.class, args);
    }
}
