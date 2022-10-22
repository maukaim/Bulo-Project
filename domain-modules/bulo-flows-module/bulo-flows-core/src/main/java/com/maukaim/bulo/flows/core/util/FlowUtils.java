package com.maukaim.bulo.flows.core.util;

import com.maukaim.bulo.flows.data.models.flow.IoDependency;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowUtils {

    public static Set<String> getInputIds(Set<IoDependency> ioDependencies) {
        return ioDependencies == null ? Set.of() : ioDependencies.stream()
                .map(IoDependency::getInputId)
                .collect(Collectors.toSet());
    }
}
