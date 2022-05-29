package com.maukaim.boule.flows.orchestrator;

import com.maukaim.boule.triggers.api.TriggerEvent;
import com.maukaim.boule.triggers.api.TriggerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/flows")
public class FlowController {
    private final FlowService flowService;

    @Autowired
    public FlowController(FlowService flowService){
        this.flowService = flowService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> startFlow(@RequestBody TriggerEvent triggerEvent) {
        this.flowService.startFlow(triggerEvent);
        return ResponseEntity.ok(true);
    }
}
