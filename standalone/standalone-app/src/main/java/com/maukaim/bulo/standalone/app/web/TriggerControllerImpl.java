package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.commons.endpoints.TriggerController;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerAddInstruction;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggerControllerImpl implements TriggerController {
    private final ScheduleTriggerService triggerService;
    private final TriggerConnector triggerConnector;

    public TriggerControllerImpl(ScheduleTriggerService triggerService,
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
