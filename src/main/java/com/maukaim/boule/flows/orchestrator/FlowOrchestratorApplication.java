package com.maukaim.boule.flows.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlowOrchestratorApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(FlowOrchestratorApplication.class, args);
        System.out.println("YO");
    }
}
