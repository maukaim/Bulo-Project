package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.endpoints.client.controller.RunClientEndpoint;
import com.maukaim.bulo.data.lifecycle.runs.client.OrchestrableContextViewFactory;
import com.maukaim.bulo.io.runs.client.OrchestrableContextView;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStoreException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlowRunController implements RunClientEndpoint {
    private FlowRunService flowRunService;

    public FlowRunController(FlowRunService flowRunService) {
        this.flowRunService = flowRunService;
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
