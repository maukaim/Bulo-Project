package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.runs.orchestrators.app.web.view.OrchestrableContextView;
import com.maukaim.bulo.runs.orchestrators.app.web.view.OrchestrableContextViewFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowRunController {
    private final FlowRunService flowRunService;

    @Autowired
    public FlowRunController(FlowRunService flowRunService) {
        this.flowRunService = flowRunService;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<OrchestrableContextView<?>> getById(@RequestParam String runId) {
        FlowRun flowRun = this.flowRunService.getById(runId);
        if (flowRun == null) {
            throw new FlowRunStoreException("No FlowRun with ID " + runId);
        } else {
            return ResponseEntity.ok(OrchestrableContextViewFactory.build(flowRun));
        }
    }
}
