package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.commons.endpoints.controller.TriggerClientEndpoint;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.io.triggers.scheduler.ScheduleTriggerAddInstruction;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggerController implements TriggerClientEndpoint {
    private final ScheduleTriggerService triggerService;
    private final TriggerConnector triggerConnector;

    public TriggerController(ScheduleTriggerService triggerService,
                             TriggerConnector triggerConnector) {
        this.triggerService = triggerService;
        this.triggerConnector = triggerConnector;
    }

    @Override
    public ResponseEntity<Boolean> removeSchedule(TriggerId triggerId) {
        boolean isRemoved = this.triggerService.removeTrigger(triggerId.getFlowId(), triggerId.getFlowStageIds());
        return ResponseEntity.ok(isRemoved);
    }

    @Override
    public ResponseEntity<?> addSchedule(ScheduleTriggerAddInstruction instruction) {
        this.triggerService.setTrigger(instruction.getTriggerId(), instruction.getCronExpression());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> oneTimeTrigger(TriggerId triggerId) {
        this.triggerConnector.requestTrigger(triggerId.getFlowId(), triggerId.getFlowStageIds());
        return ResponseEntity.ok().build();
    }
}
