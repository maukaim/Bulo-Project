package com.maukaim.bulo.app.shared.servers.model;

import java.util.HashMap;
import java.util.List;

public class DnsProperties extends HashMap<ApplicationEnvironment, List<String>> {

    public List<String> getHosts(ApplicationEnvironment env) {
        return this.getOrDefault(env, List.of());
    }
}
