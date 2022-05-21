package com.maukaim.boule.trigger.core;
import java.util.concurrent.Flow;

public interface TriggerEventPublisher{
    void publish(TriggerEvent event);
}
