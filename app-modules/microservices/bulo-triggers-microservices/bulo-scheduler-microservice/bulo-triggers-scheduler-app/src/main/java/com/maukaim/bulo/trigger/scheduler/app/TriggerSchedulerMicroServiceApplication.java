package com.maukaim.bulo.trigger.scheduler.app;

import com.maukaim.bulo.app.shared.spring.servers.StandardSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.maukaim.bulo.app.shared.system.communication.api.ServiceName.BULO_MS_TRIGGER_SCHEDULER_SERVICE;

@SpringBootApplication
public class TriggerSchedulerMicroServiceApplication extends StandardSpringApplication {
    public static void main(String[] args) {
        launchSpringApp(BULO_MS_TRIGGER_SCHEDULER_SERVICE, TriggerSchedulerMicroServiceApplication.class, args);
    }
}
