package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.executors.io.NeedStageRunEventConsumer;
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

    @Autowired
    public StageRunController(NeedStageRunEventConsumer needStageRunEventConsumer) {
        this.needStageRunEventConsumer = needStageRunEventConsumer;
    }

    @PostMapping(value = "/needRun")
    public ResponseEntity<?> needRun(@RequestBody NeedStageRunEvent event) {
        this.needStageRunEventConsumer.onStageUpdateEvent(event);
        return ResponseEntity.ok().build();
    }

}
