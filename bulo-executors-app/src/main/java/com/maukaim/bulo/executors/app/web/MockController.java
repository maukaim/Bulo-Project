package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunDependencyAdapter;
import com.maukaim.bulo.executors.data.runs.StageRunDependency;
import com.maukaim.bulo.executors.io.in.NeedStageRunEvent;
import com.maukaim.bulo.executors.io.in.model.StageRunDependencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/executors")
public class MockController {
    private final StageRunEventProcessor stageRunEventProcessor;
    private final StageRunDependencyAdapter stageRunDependencyAdapter;

    @Autowired
    public MockController(StageRunEventProcessor stageRunEventProcessor,
                          StageRunDependencyAdapter stageRunDependencyAdapter) {
        this.stageRunEventProcessor = stageRunEventProcessor;
        this.stageRunDependencyAdapter = stageRunDependencyAdapter;
    }

    @PostMapping(value = "/needRun")
    public ResponseEntity<?> needRun(@RequestBody NeedStageRunEvent event) {
        Set<StageRunDependency> stageRunDependencies = resolve(event.getDependencies());
        this.stageRunEventProcessor.onStageRunRequest(event.getGlobalStageId(),
                event.getStageRunId(), stageRunDependencies);
        return ResponseEntity.ok().build();
    }

    private Set<StageRunDependency> resolve(Set<StageRunDependencyDto> dependencies) {
        return dependencies == null ? null : dependencies.stream()
                .map(this.stageRunDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }


}
