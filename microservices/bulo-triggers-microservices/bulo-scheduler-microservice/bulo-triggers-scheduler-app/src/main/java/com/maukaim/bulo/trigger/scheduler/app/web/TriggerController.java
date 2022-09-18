package com.maukaim.bulo.trigger.scheduler.app.web;

import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConfigDto;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConsumer;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/triggers/schedule")
public class TriggerController {
    private final ScheduleTriggerConsumer scheduleTriggerConsumer;
    private final ScheduleTriggerService triggerService;
    private final TriggerConnector triggerConnector;

    public TriggerController(ScheduleTriggerConsumer scheduleTriggerConsumer,
                             ScheduleTriggerService triggerService,
                             TriggerConnector triggerConnector) {
        this.triggerConnector = triggerConnector;
        this.scheduleTriggerConsumer = scheduleTriggerConsumer;
        this.triggerService = triggerService;
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<Boolean> removeSchedule(@RequestBody TriggerId triggerId) {
        boolean isRemoved = this.triggerService.removeTrigger(triggerId.getFlowId(), triggerId.getFlowStageIds());
        return ResponseEntity.ok(isRemoved);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleTriggerConfigDto scheduleTriggerConfigDto) {
        this.scheduleTriggerConsumer.consume(scheduleTriggerConfigDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/oneTimeTrigger")
    public ResponseEntity<?> oneTimeTrigger(@RequestBody TriggerId triggerId) {
        System.out.println("Trigger -> " + triggerId);
        this.triggerConnector.requestTrigger(triggerId.getFlowId(), triggerId.getFlowStageIds());
        return ResponseEntity.ok().build();
    }
}
