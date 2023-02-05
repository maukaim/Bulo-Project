package com.maukaim.bulo.executors.app;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.spring.servers.StandardSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExecutorMsApplication extends StandardSpringApplication {
    public static void main(String[] args) {
        launchSpringApp(ServiceName.BULO_MS_EXECUTORS_SERVICE, ExecutorMsApplication.class, args);
    }
}