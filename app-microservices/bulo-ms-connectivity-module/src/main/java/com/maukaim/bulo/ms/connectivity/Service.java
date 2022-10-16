package com.maukaim.bulo.ms.connectivity;

public class Service {
    private String serviceName;
    private String rootAddress;
    private String rootPort;

    public Service(String serviceName, String rootAddress, String rootPort) {
        this.serviceName = serviceName;
        this.rootAddress = rootAddress;
        this.rootPort = rootPort;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getRootAddress() {
        return rootAddress;
    }

    public String getRootPort() {
        return rootPort;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceName='" + serviceName + '\'' +
                ", rootAddress='" + rootAddress + '\'' +
                ", rootPort='" + rootPort + '\'' +
                '}';
    }
}
