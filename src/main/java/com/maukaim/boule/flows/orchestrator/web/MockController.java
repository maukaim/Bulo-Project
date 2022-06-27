package com.maukaim.boule.flows.orchestrator.web;

import com.maukaim.boule.flows.orchestrator.run.FlowRun;
import com.maukaim.boule.flows.orchestrator.run.FlowRunService;
import com.maukaim.boule.flows.orchestrator.stage.StageEvent;
import com.maukaim.boule.flows.orchestrator.stage.event.*;
import com.maukaim.boule.flows.orchestrator.stage.StageEventManager;
import com.maukaim.boule.flows.orchestrator.web.view.FlowRunView;
import com.maukaim.boule.flows.orchestrator.web.view.FlowRunViewFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mockStageEventFromExecutor")
public class MockController {

    private final StageEventManager stageEventManager;
    private final FlowRunService flowRunService;

    @Autowired
    public MockController(StageEventManager stageEventManager,
                          FlowRunService flowRunService){
        this.stageEventManager = stageEventManager;
        this.flowRunService = flowRunService;
    }

    @PostMapping(value = "/stageRunAcknowledged")
    public ResponseEntity<FlowRunView> stageRunAcknowledged(@RequestBody AcknowledgeRequestStageEvent event) {
        return standardProcess(event);
    }

    @PostMapping(value = "/stageRunStart")
    public ResponseEntity<FlowRunView> startStageRun(@RequestBody StartRunStageEvent event) {
        return standardProcess(event);
    }

    @PostMapping(value = "/stageRunFailed")
    public ResponseEntity<FlowRunView> stageRunHasFailed(@RequestBody RunFailedStageEvent event) {
        return standardProcess(event);
    }

    @PostMapping(value = "/stageRunCancelled")
    public ResponseEntity<FlowRunView> stageRunHasCancelled(@RequestBody RunCancelledStageEvent event) {
        return standardProcess(event);
    }

    @PostMapping(value = "/stageRunSuccess")
    public ResponseEntity<FlowRunView> stageRunSucceed(@RequestBody RunSuccessfulStageEvent event) {
        return standardProcess(event);
    }

    private ResponseEntity<FlowRunView> standardProcess(StageEvent event){
        this.stageEventManager.process(event);
        return ResponseEntity.ok(getLatestView(event.getFlowRunId()));
    }

    private FlowRunView getLatestView(String flowRunId) {
        FlowRun concernedFlowRun = this.flowRunService.getById(flowRunId);
        return FlowRunViewFactory.build(concernedFlowRun);
    }

}
