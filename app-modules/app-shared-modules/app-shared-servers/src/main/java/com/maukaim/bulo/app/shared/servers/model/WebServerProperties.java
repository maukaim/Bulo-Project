package com.maukaim.bulo.app.shared.servers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebServerProperties {
    private int port;
    private DnsProperties dnsProperties;

    public WebServerProperties(@JsonProperty("port") int port,
                               @JsonProperty("dns") DnsProperties dnsProperties) {
        this.port = port;
        this.dnsProperties = dnsProperties;
    }

    public int getPort() {
        return port;
    }

    public DnsProperties getDnsProperties() {
        return dnsProperties;
    }

    @Override
    public String toString() {
        return "WebServerProperties{" +
                "port=" + port +
                ", dnsProperties=" + dnsProperties +
                '}';
    }
}
