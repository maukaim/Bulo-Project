package com.maukaim.boule.trigger.scheduler.web;

import com.maukaim.boule.trigger.core.TriggerId;
import com.maukaim.boule.trigger.scheduler.ScheduleTriggerConfig;
import com.maukaim.boule.trigger.scheduler.ScheduleTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/triggers/schedule")
public class TriggerController {

    private final ScheduleTriggerService triggerService;

    public TriggerController(@Autowired ScheduleTriggerService triggerService) {
        this.triggerService = triggerService;
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<Boolean> removeSchedule(@RequestParam String flowId,
                                                  @RequestParam String stageId) {
        TriggerId triggerId = TriggerId.of(flowId, stageId);
        boolean isRemoved = this.triggerService.removeTrigger(triggerId.getFlowId(), triggerId.getStageId());
        return ResponseEntity.ok(isRemoved);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ScheduleTriggerConfig> addSchedule(@RequestBody ScheduleTriggerConfig scheduleTriggerConfig) {
        return ResponseEntity.ok(this.triggerService.setTrigger(scheduleTriggerConfig));
    }
}
