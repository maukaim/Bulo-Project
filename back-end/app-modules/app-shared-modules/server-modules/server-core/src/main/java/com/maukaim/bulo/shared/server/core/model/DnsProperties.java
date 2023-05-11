package com.maukaim.bulo.shared.server.core.model;

import java.util.HashMap;
import java.util.List;

public class DnsProperties extends HashMap<ApplicationEnvironment, List<String>> {

    public List<String> getHosts(ApplicationEnvironment env) {
        return this.getOrDefault(env, List.of());
    }
}
