package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.runs.orchestrators.app.web.view.FlowRunView;
import com.maukaim.bulo.runs.orchestrators.app.web.view.FlowRunViewFactory;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.FlowRunStoreException;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRun;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.io.FlowRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.TriggerEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.both.FlowRunEvent;
import com.maukaim.bulo.runs.orchestrators.io.in.BasicTriggerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flows/runs")
public class FlowRunController {
    private final FlowRunService flowRunService;
    private final TriggerEventConsumer triggerEventConsumer;
    private final FlowRunEventConsumer flowRunEventConsumer;

    @Autowired
    public FlowRunController(FlowRunEventConsumer flowRunEventConsumer,
            TriggerEventConsumer triggerEventConsumer,
            FlowRunService flowRunService) {
        this.triggerEventConsumer = triggerEventConsumer;
        this.flowRunEventConsumer = flowRunEventConsumer;
        this.flowRunService = flowRunService;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<FlowRunView> getById(@RequestParam String runId) {
        FlowRun flowRun = this.flowRunService.getById(runId);
        if (flowRun == null) {
            throw new FlowRunStoreException("No FlowRun with ID " + runId);
        } else {
            return ResponseEntity.ok(FlowRunViewFactory.build(flowRun));
        }
    }

    @PostMapping(value = "/startFlowRun")
    public ResponseEntity<FlowRunView> startFlow(@RequestBody BasicTriggerEvent triggerEvent) {
        String flowRunId = this.triggerEventConsumer.onTriggerEvent(triggerEvent);
        FlowRun flowRun = this.flowRunService.getById(flowRunId);
        return ResponseEntity.ok(FlowRunViewFactory.build(flowRun));
    }

    @PostMapping(value = "/onUpdate")
    public ResponseEntity<FlowRunView> startFlow(@RequestBody FlowRunEvent flowRunEvent) {
        this.flowRunEventConsumer.onFlowRunEvent(flowRunEvent);
        FlowRun flowRunPersisted = this.flowRunService.getById(flowRunEvent.getFlowRunDto().getFlowRunId());
        return ResponseEntity.ok(FlowRunViewFactory.build(flowRunPersisted));
    }
}
