package com.maukaim.bulo.runs.orchestrators.app.web;

import com.maukaim.bulo.app.endpoints.client.controller.RunClientEndpoint;
import com.maukaim.bulo.io.runs.client.OrchestrableContextView;
import com.maukaim.bulo.data.lifecycle.runs.client.OrchestrableContextViewFactory;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStoreException;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlowRunController implements RunClientEndpoint {
    private final FlowRunService flowRunService;

    @Autowired
    public FlowRunController(FlowRunService flowRunService) {
        this.flowRunService = flowRunService;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<OrchestrableContextView<?,?>> getById(@RequestParam String runId) {
        FlowRun flowRun = this.flowRunService.getById(runId);
        if (flowRun == null) {
            throw new FlowRunStoreException("No FlowRun with ID " + runId);
        } else {
            return ResponseEntity.ok(OrchestrableContextViewFactory.build(flowRun));
        }
    }

    @Override
    public ResponseEntity<List<OrchestrableContextView<?,?>>> getByFlowId(String flowId) {
        List<? extends OrchestrableContextView<?,?>> orchestrableContextViews = this.flowRunService.getByFlowId(flowId).stream()
                .map(OrchestrableContextViewFactory::build)
                .toList();
        if(orchestrableContextViews.isEmpty()){
            throw new FlowRunStoreException("No FlowRun with Flow ID " + flowId);
        }
        return ResponseEntity.ok((List<OrchestrableContextView<?,?>>) orchestrableContextViews);
    }
}
