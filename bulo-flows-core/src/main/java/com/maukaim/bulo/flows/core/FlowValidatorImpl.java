package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.flows.io.FlowData;

public class FlowValidatorImpl implements FlowValidator {

    @Override
    public boolean validate(FlowData flowData) {
        new AcyclicValidator(flowData.getAncestorsByStageId()).validate(); // Le FlowData devrait avoir une map de ancestorSByStage
        return true;
    }

    @Override
    public boolean validate(FlowData flowData, Flow existingFlow) {
        return false;
    }
}
