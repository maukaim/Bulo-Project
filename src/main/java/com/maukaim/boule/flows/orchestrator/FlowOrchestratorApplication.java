package com.maukaim.boule.flows.orchestrator;

import com.maukaim.boule.flows.orchestrator.flow.model.ExecutionGraph;
import com.maukaim.boule.flows.orchestrator.flow.model.UnmodifiableAcyclicExecutionGraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class FlowOrchestratorApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(FlowOrchestratorApplication.class, args);
        ExecutionGraph executionGraph = new UnmodifiableAcyclicExecutionGraph(Map.of(
                "1", Set.of(),
                "2", Set.of()
        ));
    }
}
