package com.maukaim.bulo.app.commons.endpoints;

import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerAddInstruction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/triggers/schedule")
public interface TriggerController {
    @DeleteMapping(value = "/remove")
    ResponseEntity<Boolean> removeSchedule(@RequestBody TriggerId triggerId);

    @PostMapping(value = "/add")
    ResponseEntity<?> addSchedule(@RequestBody ScheduleTriggerAddInstruction scheduleTriggerAddInstruction);

    @PostMapping(value = "/oneTimeTrigger")
    ResponseEntity<?> oneTimeTrigger(@RequestBody TriggerId triggerId);
}
