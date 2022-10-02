package com.maukaim.bulo.app.commons.endpoints;

import com.maukaim.bulo.flows.io.events.CreateFlowInstruction;
import com.maukaim.bulo.flows.io.events.RemoveFlowInstruction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "api/v1/flows")
public interface FlowController {
    @PostMapping(value = "/createOrUpdate", consumes = {"application/json"})
    void onCreate(@RequestBody CreateFlowInstruction instruction);

    @PostMapping(value = "/remove")
    void onRemove(@RequestBody RemoveFlowInstruction instruction);
}
