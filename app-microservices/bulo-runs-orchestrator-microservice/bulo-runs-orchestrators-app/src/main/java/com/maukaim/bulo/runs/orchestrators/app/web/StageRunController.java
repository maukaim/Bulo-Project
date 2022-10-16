package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.runs.orchestrators.app.web.view.FlowRunView;
import com.maukaim.bulo.runs.orchestrators.app.web.view.FlowRunViewFactory;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.io.StageRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.events.*;
import com.maukaim.bulo.commons.io.IStageRunEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orchestrator/stageRuns")
public class StageRunController {
    private final StageRunEventConsumer stageRunEventConsumer;
    private final FlowRunService flowRunService;
    private final StageRunService stageRunService;

    @Autowired
    public StageRunController(StageRunEventConsumer stageRunEventConsumer,
                              FlowRunService flowRunService,
                              StageRunService stageRunService){
        this.stageRunEventConsumer = stageRunEventConsumer;
        this.flowRunService = flowRunService;
        this.stageRunService = stageRunService;
    }

    @PostMapping(value="/onStageRunEvent")
    public void onStageRunEvent(@RequestBody BasicStageRunEvent event){
        new Thread(()-> {
            switch (event.getEventType()) {
                case ACKNOWLEDGE_REQUEST -> stageRunAcknowledged((AcknowledgeRequestStageRunEvent) event);
                case LAUNCH_RUN -> startStageRun((StartRunStageRunEvent) event);
                case RUN_CANCELLED -> stageRunHasCancelled((RunCancelledStageRunEvent) event);
                case RUN_FAILED -> stageRunHasFailed((RunFailedStageRunEvent) event);
                case RUN_SUCCESSFUL -> stageRunSucceed((RunSuccessfulStageRunEvent) event);
            }
        }).start();
    }

    public ResponseEntity<FlowRunView> stageRunAcknowledged(@RequestBody AcknowledgeRequestStageRunEvent event) {
        return standardProcess(event);
    }

    public ResponseEntity<FlowRunView> startStageRun(@RequestBody StartRunStageRunEvent event) {
        return standardProcess(event);
    }

    public ResponseEntity<FlowRunView> stageRunHasFailed(@RequestBody RunFailedStageRunEvent event) {
        return standardProcess(event);
    }

    public ResponseEntity<FlowRunView> stageRunHasCancelled(@RequestBody RunCancelledStageRunEvent event) {
        return standardProcess(event);
    }

    public ResponseEntity<FlowRunView> stageRunSucceed(@RequestBody RunSuccessfulStageRunEvent event) {
        return standardProcess(event);
    }

    private ResponseEntity<FlowRunView> standardProcess(IStageRunEvent event){
        this.stageRunEventConsumer.onStageRunEvent(event);
        StageRun stageRun = this.stageRunService.getById(event.getStageRunId());
        return ResponseEntity.ok(getLatestView(stageRun.getFlowRunId()));
    }

    private FlowRunView getLatestView(String flowRunId) {
        FlowRun concernedFlowRun = this.flowRunService.getById(flowRunId);
        return FlowRunViewFactory.build(concernedFlowRun);
    }
}
