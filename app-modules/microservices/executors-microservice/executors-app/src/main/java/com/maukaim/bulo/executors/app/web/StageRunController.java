package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.io.executors.NeedStageRunCancelEventConsumer;
import com.maukaim.bulo.io.executors.in.CancelRunInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/executors/runs")
public class StageRunController {
    private final NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer;

    @Autowired
    public StageRunController(NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer) {
        this.needStageRunCancelEventConsumer = needStageRunCancelEventConsumer;
    }

    @PostMapping(value = "/client/needCancel")
    public ResponseEntity<?> needRun(@RequestBody CancelRunInstruction instruction) {
        Thread thread = new Thread(() -> this.needStageRunCancelEventConsumer.consume(instruction));
        thread.start();
        return ResponseEntity.ok().build();
    }

}
