package com.maukaim.bulo.runs.orchestrator.app.web;

import com.maukaim.bulo.runs.orchestrator.app.web.view.FlowRunView;
import com.maukaim.bulo.runs.orchestrator.app.web.view.FlowRunViewFactory;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.model.FlowRun;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.io.in.AcknowledgeRequestStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.io.in.RunCancelledStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.io.in.RunFailedStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.io.in.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.common.io.StageRunEvent;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageEventManager;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunCache;
import com.maukaim.bulo.runs.orchestrator.io.in.StartRunStageRunEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mockStageEventFromExecutor")
public class MockController {

    private final StageEventManager stageEventManager;
    private final FlowRunService flowRunService;
    private final StageRunCache stageRuncache;

    @Autowired
    public MockController(StageEventManager stageEventManager,
                          FlowRunService flowRunService,
                          StageRunCache stageRunCache){
        this.stageEventManager = stageEventManager;
        this.flowRunService = flowRunService;
        this.stageRuncache = stageRunCache;
    }

    @PostMapping(value = "/stageRunAcknowledged")
    public ResponseEntity<FlowRunView> stageRunAcknowledged(@RequestBody AcknowledgeRequestStageRunEvent event) {
        return standardProcess(event);
    }

    @PostMapping(value = "/stageRunStart")
    public ResponseEntity<FlowRunView> startStageRun(@RequestBody StartRunStageRunEvent event) {
        return standardProcess(event);
    }

    @PostMapping(value = "/stageRunFailed")
    public ResponseEntity<FlowRunView> stageRunHasFailed(@RequestBody RunFailedStageRunEvent event) {
        return standardProcess(event);
    }

    @PostMapping(value = "/stageRunCancelled")
    public ResponseEntity<FlowRunView> stageRunHasCancelled(@RequestBody RunCancelledStageRunEvent event) {
        return standardProcess(event);
    }

    @PostMapping(value = "/stageRunSuccess")
    public ResponseEntity<FlowRunView> stageRunSucceed(@RequestBody RunSuccessfulStageRunEvent event) {
        return standardProcess(event);
    }

    private ResponseEntity<FlowRunView> standardProcess(StageRunEvent event){
        this.stageEventManager.process(event);
        return ResponseEntity.ok(getLatestView(this.stageRuncache.getFlowRunId(event.getStageRunId())));
    }

    private FlowRunView getLatestView(String flowRunId) {
        FlowRun concernedFlowRun = this.flowRunService.getById(flowRunId);
        return FlowRunViewFactory.build(concernedFlowRun);
    }

}
