package com.maukaim.bulo.runs.orchestrators.io.out.model;

import java.util.*;
import java.util.stream.Collectors;

public class IoMapping extends HashMap<String, HashMap<String, Set<String>>> {
    public Set<String> getAncestorIds() {
        return this.values().stream()
                .map(outputNamesByAncestors -> outputNamesByAncestors.keySet())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public Set<String> getInputIds() {
        return this.keySet();
    }

    public Map<String, Set<String>> getOutputsByAncestors(String inputId) {
        return this.get(inputId);
    }

    public Map<String, Set<String>> getInputIdsByOutputIds(String ancestorId) {
        return this.entrySet().stream()
                .map(entry -> {
                    Map<String, Set<String>> outputIdsByAncestorIds = entry.getValue();
                    Set<String> outputIds = outputIdsByAncestorIds.get(ancestorId);
                    if (outputIds == null) {
                        return null;
                    } else {
                        return Map.entry(entry.getKey(), outputIds);
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }
}
