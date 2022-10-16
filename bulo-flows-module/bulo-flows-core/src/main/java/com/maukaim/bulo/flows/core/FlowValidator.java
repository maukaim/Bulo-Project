package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.flow.Flow;

public interface FlowValidator {
    boolean validate(Flow flow) throws FlowValidationException;
}
