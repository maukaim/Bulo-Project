package com.maukaim.boule.flows.orchestrator.web;

import com.maukaim.boule.flows.orchestrator.flow.run.FlowRun;
import com.maukaim.boule.flows.orchestrator.flow.run.FlowRunCacheException;
import com.maukaim.boule.flows.orchestrator.flow.FlowRunService;
import com.maukaim.boule.flows.orchestrator.flow.run.FlowRunServiceImpl;
import com.maukaim.boule.flows.orchestrator.flow.view.FlowStageId;
import com.maukaim.boule.flows.orchestrator.web.view.FlowRunView;
import com.maukaim.boule.flows.orchestrator.web.view.FlowRunViewFactory;
import com.maukaim.boule.triggers.api.TriggerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/flows/runs")
public class FlowRunController {
    private final FlowRunService flowRunService;

    @Autowired
    public FlowRunController(FlowRunServiceImpl flowRunService) {
        this.flowRunService = flowRunService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<FlowRunView> startFlow(@RequestBody TriggerEvent triggerEvent) {
        Set<FlowStageId> flowStageIds = triggerEvent.getTriggerId().getStageIds().stream()
                .map(FlowStageId::of)
                .collect(Collectors.toUnmodifiableSet());
        FlowRun flowRun = this.flowRunService.startRun(triggerEvent.getTriggerId().getFlowId(), flowStageIds);
        return ResponseEntity.ok(FlowRunViewFactory.build(flowRun));
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<FlowRunView> getById(@RequestParam String runId) {
        FlowRun flowRun = this.flowRunService.getById(runId);
        if (flowRun == null) {
            throw new FlowRunCacheException("No FlowRun with ID " + runId);
        } else {
            return ResponseEntity.ok(FlowRunViewFactory.build(flowRun));
        }
    }
}
