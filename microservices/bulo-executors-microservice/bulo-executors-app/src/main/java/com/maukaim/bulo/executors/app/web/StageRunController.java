package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.executors.io.NeedStageRunCancelEventConsumer;
import com.maukaim.bulo.executors.io.NeedStageRunEventConsumer;
import com.maukaim.bulo.executors.io.in.NeedStageRunCancelEvent;
import com.maukaim.bulo.executors.io.in.NeedStageRunEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/executors/runs")
public class StageRunController {
    private final NeedStageRunEventConsumer needStageRunEventConsumer;
    private final NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer;

    @Autowired
    public StageRunController(NeedStageRunEventConsumer needStageRunEventConsumer,
                              NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer) {
        this.needStageRunEventConsumer = needStageRunEventConsumer;
        this.needStageRunCancelEventConsumer = needStageRunCancelEventConsumer;
    }

    @PostMapping(value = "/needRun")
    public ResponseEntity<?> needRun(@RequestBody NeedStageRunEvent event) {
        Thread thread = new Thread(() -> this.needStageRunEventConsumer.consume(event));
        thread.start();
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/needCancel")
    public ResponseEntity<?> needRun(@RequestBody NeedStageRunCancelEvent event) {
        Thread thread = new Thread(() -> this.needStageRunCancelEventConsumer.consume(event));
        thread.start();
        return ResponseEntity.ok().build();
    }

}
