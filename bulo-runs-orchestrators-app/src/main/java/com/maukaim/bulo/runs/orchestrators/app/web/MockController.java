package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.runs.orchestrators.app.web.view.FlowRunView;
import com.maukaim.bulo.runs.orchestrators.app.web.view.FlowRunViewFactory;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRun;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.io.StageRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.in.AcknowledgeRequestStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.io.in.RunCancelledStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.io.in.RunFailedStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.io.in.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.commons.io.IStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.io.in.StartRunStageRunEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mockStageEventFromExecutor")
public class MockController {

    private final StageRunEventConsumer stageRunEventConsumer;
    private final FlowRunService flowRunService;
    private final StageRunStore stageRuncache;

    @Autowired
    public MockController(StageRunEventConsumer stageRunEventConsumer,
                          FlowRunService flowRunService,
                          StageRunStore stageRunStore){
        this.stageRunEventConsumer = stageRunEventConsumer;
        this.flowRunService = flowRunService;
        this.stageRuncache = stageRunStore;
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

    private ResponseEntity<FlowRunView> standardProcess(IStageRunEvent event){
        this.stageRunEventConsumer.onStageRunEvent(event);
        return ResponseEntity.ok(getLatestView(this.stageRuncache.getFlowRunId(event.getStageRunId())));
    }

    private FlowRunView getLatestView(String flowRunId) {
        FlowRun concernedFlowRun = this.flowRunService.getById(flowRunId);
        return FlowRunViewFactory.build(concernedFlowRun);
    }

}
