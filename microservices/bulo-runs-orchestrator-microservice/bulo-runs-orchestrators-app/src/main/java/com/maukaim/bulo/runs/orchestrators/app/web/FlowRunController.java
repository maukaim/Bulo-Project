package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.runs.orchestrators.app.web.view.FlowRunView;
import com.maukaim.bulo.runs.orchestrators.app.web.view.FlowRunViewFactory;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStoreException;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.io.FlowRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.TriggerEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.events.FlowRunEvent;
import com.maukaim.bulo.runs.orchestrators.io.events.FlowRunInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orchestrator/flowRuns")
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
    public void startFlow(@RequestBody FlowRunInstruction instruction) {
        new Thread(()-> this.triggerEventConsumer.onFlowRunInstruction(instruction)).start();
    }

    @PostMapping(value = "/onUpdate")
    public void flowRunEvent(@RequestBody FlowRunEvent flowRunEvent) {
        new Thread(()->this.flowRunEventConsumer.onFlowRunEvent(flowRunEvent)).start();
    }
}
