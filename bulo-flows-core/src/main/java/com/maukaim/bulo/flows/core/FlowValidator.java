package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.io.FlowData;

public interface FlowValidator {
    boolean validate(FlowData flowData);
    boolean validate(FlowData flowData, Flow existingFlow);
}
