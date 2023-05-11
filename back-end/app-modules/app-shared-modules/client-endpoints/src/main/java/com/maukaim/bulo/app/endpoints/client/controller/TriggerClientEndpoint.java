package com.maukaim.bulo.app.endpoints.client.controller;

import com.maukaim.bulo.app.endpoints.client.ForClientEventType;
import com.maukaim.bulo.app.endpoints.client.ClientEventType;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.io.scheduler.client.CreateScheduleTriggerInstruction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_TRIGGER_SCHEDULER_SERVICE, ServiceName.STANDALONE})
@RequestMapping("api/v1/triggers/schedule")
public interface TriggerClientEndpoint {
    @ForClientEventType(ClientEventType.TRIGGER_REMOVE_SCHEDULED)
    @DeleteMapping(value = "/remove")
    ResponseEntity<Boolean> removeSchedule(@RequestBody TriggerId triggerId);

    @ForClientEventType(ClientEventType.TRIGGER_ADD_SCHEDULED)
    @PostMapping(value = "/add")
    ResponseEntity<?> addSchedule(@RequestBody CreateScheduleTriggerInstruction createScheduleTriggerInstruction);

    @ForClientEventType(ClientEventType.TRIGGER_MANUAL_ONETIME)
    @PostMapping(value = "/oneTimeTrigger")
    ResponseEntity<?> oneTimeTrigger(@RequestBody TriggerId triggerId);
}
