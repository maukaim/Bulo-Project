package com.maukaim.bulo.runs.orchestrators.app.io;

public class DummyPublisherAbstract<T> {
    protected final static String MESSAGE_PREFIX = "Fake publish to KAFKA ! ::: ";

    public boolean publish(T evt) {
        System.out.println(MESSAGE_PREFIX + evt);
        return true;
    }
}
