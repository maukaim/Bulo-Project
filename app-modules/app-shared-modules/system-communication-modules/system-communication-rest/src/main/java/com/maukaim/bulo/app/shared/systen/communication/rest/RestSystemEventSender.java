package com.maukaim.bulo.app.shared.systen.communication.rest;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.core.SystemEventSender;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RestSystemEventSender implements SystemEventSender {
    private final ServiceName serviceName;
    private final List<String> potentialHosts;
    private final String rootPort;
    private final String servicePath;

    public RestSystemEventSender(ServiceName serviceName, Collection<String> potentialHosts, String rootPort, String servicePath) {
        this.serviceName = serviceName;
        this.potentialHosts = List.copyOf(potentialHosts);
        this.rootPort = rootPort;
        this.servicePath = servicePath;
    }

    @Override
    public String getIdentifier() {
        return String.format("%s[%s]", serviceName, servicePath);
    }

    public List<String> getPotentialUris() {
        return potentialHosts.stream()
                .map(host -> String.format("http://%s:%s/%s",
                        host,
                        rootPort,
                        servicePath.indexOf('/') == 0 ?
                                servicePath.substring(1) : servicePath)
                )
                .collect(Collectors.toList());
    }
}
