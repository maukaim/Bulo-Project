package com.maukaim.bulo.app.commons.endpoints.controller;

import com.maukaim.bulo.app.commons.endpoints.ForClientEventType;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.io.triggers.scheduler.ScheduleTriggerAddInstruction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.maukaim.bulo.app.commons.endpoints.ClientEventType.*;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_TRIGGER_SCHEDULER_SERVICE, ServiceName.STANDALONE})
@RequestMapping("api/v1/triggers/schedule")
public interface TriggerClientEndpoint {
    @ForClientEventType(TRIGGER_REMOVE_SCHEDULED)
    @DeleteMapping(value = "/remove")
    ResponseEntity<Boolean> removeSchedule(@RequestBody TriggerId triggerId);

    @ForClientEventType(TRIGGER_ADD_SCHEDULED)
    @PostMapping(value = "/add")
    ResponseEntity<?> addSchedule(@RequestBody ScheduleTriggerAddInstruction scheduleTriggerAddInstruction);

    @ForClientEventType(TRIGGER_MANUAL_ONETIME)
    @PostMapping(value = "/oneTimeTrigger")
    ResponseEntity<?> oneTimeTrigger(@RequestBody TriggerId triggerId);
}
