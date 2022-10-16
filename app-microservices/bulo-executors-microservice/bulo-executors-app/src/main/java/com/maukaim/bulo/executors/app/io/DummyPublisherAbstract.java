package com.maukaim.bulo.executors.app.io;

public class DummyPublisherAbstract<T> {

    public boolean publish(T evt){
        System.out.println("Fake publish to KAFKA ! ::: " + evt);
        return true;
    }
}
