package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.commons.io.IStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.app.web.view.OrchestrableContextView;
import com.maukaim.bulo.runs.orchestrators.app.web.view.OrchestrableContextViewFactory;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;
import com.maukaim.bulo.runs.orchestrators.io.StageRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.events.*;
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
                              StageRunService stageRunService) {
        this.stageRunEventConsumer = stageRunEventConsumer;
        this.flowRunService = flowRunService;
        this.stageRunService = stageRunService;
    }

    @PostMapping(value = "/onStageRunEvent")
    public void onStageRunEvent(@RequestBody BasicStageRunEvent event) {
        new Thread(() -> {
            switch (event.getEventType()) {
                case ACKNOWLEDGE_REQUEST -> stageRunAcknowledged((AcknowledgeRequestStageRunEvent) event);
                case LAUNCH_RUN -> startStageRun((StartRunStageRunEvent) event);
                case RUN_CANCELLED -> stageRunHasCancelled((RunCancelledStageRunEvent) event);
                case RUN_FAILED -> stageRunHasFailed((RunFailedStageRunEvent) event);
                case RUN_SUCCESSFUL -> stageRunSucceed((RunSuccessfulStageRunEvent) event);
            }
        }).start();
    }

    public ResponseEntity<OrchestrableContextView> stageRunAcknowledged(@RequestBody AcknowledgeRequestStageRunEvent event) {
        return standardProcess(event);
    }

    public ResponseEntity<OrchestrableContextView> startStageRun(@RequestBody StartRunStageRunEvent event) {
        return standardProcess(event);
    }

    public ResponseEntity<OrchestrableContextView> stageRunHasFailed(@RequestBody RunFailedStageRunEvent event) {
        return standardProcess(event);
    }

    public ResponseEntity<OrchestrableContextView> stageRunHasCancelled(@RequestBody RunCancelledStageRunEvent event) {
        return standardProcess(event);
    }

    public ResponseEntity<OrchestrableContextView> stageRunSucceed(@RequestBody RunSuccessfulStageRunEvent event) {
        return standardProcess(event);
    }

    private ResponseEntity<OrchestrableContextView> standardProcess(IStageRunEvent event) {
        this.stageRunEventConsumer.onStageRunEvent(event);
        StageRun stageRun = this.stageRunService.getById(event.getStageRunId());
        return ResponseEntity.ok(getLatestView(stageRun.getContext()));
    }

    private OrchestrableContextView getLatestView(Context context) {
        OrchestrableContext<?> orchestrableContext = switch (context.getContextType()){
            case FLOW_RUN -> this.flowRunService.getById(((FlowContext)context).getContextId());
            case FUNCTIONAL_STAGE_RUN -> (FunctionalStageRun)this.stageRunService.getById(((FunctionalStageContext)context).getContextId());
        };
        return OrchestrableContextViewFactory.build(orchestrableContext);
    }
}
