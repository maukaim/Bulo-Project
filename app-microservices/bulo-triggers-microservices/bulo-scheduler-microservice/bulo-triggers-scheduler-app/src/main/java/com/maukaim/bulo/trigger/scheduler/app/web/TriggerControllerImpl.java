package com.maukaim.bulo.trigger.scheduler.app.web;

import com.maukaim.bulo.app.commons.endpoints.TriggerController;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerAddInstruction;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConsumer;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggerControllerImpl implements TriggerController {
    private final ScheduleTriggerConsumer scheduleTriggerConsumer;
    private final ScheduleTriggerService triggerService;
    private final TriggerConnector triggerConnector;

    public TriggerControllerImpl(ScheduleTriggerConsumer scheduleTriggerConsumer,
                                 ScheduleTriggerService triggerService,
                                 TriggerConnector triggerConnector) {
        this.triggerConnector = triggerConnector;
        this.scheduleTriggerConsumer = scheduleTriggerConsumer;
        this.triggerService = triggerService;
    }

    @Override
    public ResponseEntity<Boolean> removeSchedule(@RequestBody TriggerId triggerId) {
        boolean isRemoved = this.triggerService.removeTrigger(triggerId.getFlowId(), triggerId.getFlowStageIds());
        return ResponseEntity.ok(isRemoved);
    }

    @Override
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleTriggerAddInstruction scheduleTriggerAddInstruction) {
        this.scheduleTriggerConsumer.consume(scheduleTriggerAddInstruction);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> oneTimeTrigger(@RequestBody TriggerId triggerId) {
        System.out.println("Trigger -> " + triggerId);
        this.triggerConnector.requestTrigger(triggerId.getFlowId(), triggerId.getFlowStageIds());
        return ResponseEntity.ok().build();
    }
}