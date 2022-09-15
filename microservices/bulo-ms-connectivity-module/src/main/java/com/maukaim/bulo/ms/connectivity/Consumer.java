package com.maukaim.bulo.ms.connectivity;

public class Consumer {
    private Service service;
    private String pathAppender;

    public Consumer(Service service, String pathAppender) {
        this.service = service;
        this.pathAppender = pathAppender;
    }

    public Service getService() {
        return service;
    }

    public String getPathAppender() {
        return pathAppender;
    }

    public String toUrl() {
        return String.format("http://%s:%s/%s",
                getService().getRootAddress(),
                getService().getRootPort(),
                getPathAppender().indexOf('/') == 0 ?
                        getPathAppender().substring(1) : getPathAppender()
        );
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "service=" + service +
                ", pathAppender='" + pathAppender + '\'' +
                '}';
    }
}
