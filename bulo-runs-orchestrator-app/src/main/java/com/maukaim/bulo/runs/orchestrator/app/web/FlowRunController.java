package com.maukaim.bulo.runs.orchestrator.app.web;

import com.maukaim.bulo.flows.api.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.app.web.view.FlowRunView;
import com.maukaim.bulo.runs.orchestrator.app.web.view.FlowRunViewFactory;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.model.FlowRun;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunCacheException;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunService;
import com.maukaim.bulo.triggers.api.TriggerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/flows/runs")
public class FlowRunController {
    private final FlowRunService flowRunService;

    @Autowired
    public FlowRunController(FlowRunService flowRunService) {
        this.flowRunService = flowRunService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<FlowRunView> startFlow(@RequestBody TriggerEvent triggerEvent) {
        Set<FlowStageId> flowStageIds = triggerEvent.getTriggerId().getFlowStageIds();
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
