package com.maukaim.bulo.executor.app.web;

import com.maukaim.bulo.executor.core.StageRunEventProcessor;
import com.maukaim.bulo.executor.io.in.NeedStageRunEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/executors")
public class MockController {
    private final StageRunEventProcessor stageRunEventProcessor;

    @Autowired
    public MockController(StageRunEventProcessor stageRunEventProcessor) {
        this.stageRunEventProcessor = stageRunEventProcessor;
    }

    @PostMapping(value = "/needRun")
    public ResponseEntity<?> needRun(@RequestBody NeedStageRunEvent event) {
        this.stageRunEventProcessor.onStageRunRequest(event.getGlobalStageId(),
                event.getStageRunId(), event.getAncestorsOutputByInputName());
        return ResponseEntity.ok().build();
    }


}
