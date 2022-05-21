package com.maukaim.boule.trigger.scheduler;

import com.maukaim.boule.trigger.core.DummyTriggerEventPublisher;
import com.maukaim.boule.trigger.core.TriggerEvent;
import com.maukaim.boule.trigger.core.TriggerId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class TriggerSchedulerApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(TriggerSchedulerApplication.class, args);
//        ScheduleTriggerService scheduleTriggerService = new ScheduleTriggerService(new DummyTriggerEventPublisher());
//        ScheduleTriggerConfig scheduleTriggerConfig = new ScheduleTriggerConfig(TriggerId.of("flow", "stage"), "*/5 * * * * *");
//        scheduleTriggerService.setTrigger(scheduleTriggerConfig);
//        Thread.sleep(50000);
//        System.out.println("Hihi" + new TriggerEvent(TriggerId.of("ExcelPointage", "XlsReader"),
//                Instant.now())
//        );
    }
}
