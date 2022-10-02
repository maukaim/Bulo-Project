package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStoreException;
import com.maukaim.bulo.runs.orchestrators.io.FlowEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.events.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/orchestrator/flows")
public class FlowController {
    private final FlowService flowService;
    private final FlowEventConsumer flowEventConsumer;

    @Autowired
    public FlowController(FlowEventConsumer flowEventConsumer,
                          FlowService flowService) {
        this.flowService = flowService;
        this.flowEventConsumer = flowEventConsumer;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<Flow> getById(@RequestParam String flowId) {
        Optional<Flow> flow = this.flowService.getFlow(flowId);
        if (flow.isEmpty()) {
            throw new FlowRunStoreException("No Flow with ID " + flowId);
        } else {
            return ResponseEntity.ok(flow.get());
        }
    }

    @PostMapping(value = "/onUpdate")
    public ResponseEntity<Flow> flowEvent(@RequestBody FlowEvent flowEvent) {
        this.flowEventConsumer.onFlowEvent(flowEvent);
        Optional<Flow> flow = this.flowService.getFlow(flowEvent.getFlow().getFlowId());
        return ResponseEntity.ok(flow.get());
    }
}
