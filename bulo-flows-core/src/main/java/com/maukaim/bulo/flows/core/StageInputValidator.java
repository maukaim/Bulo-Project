package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;

import java.util.Set;

public interface StageInputValidator {
    void validate(Set<IoDependency> ioDependencies, StageDefinition definition) throws FlowValidationException;
}
