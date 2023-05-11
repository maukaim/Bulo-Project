package com.maukaim.bulo.ms.shared.spring.servers;

import com.maukaim.bulo.app.shared.system.communication.core.MessageTransmissionException;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

import java.util.List;

public class CompoundSystemConnector implements SystemConnector<MicroServiceEventType> {
    private final SystemConnector<MicroServiceEventType> mainSystemConnector;
    private final SystemConnector<MicroServiceEventType> secondSystemConnector;

    public CompoundSystemConnector(SystemConnector<MicroServiceEventType> mainSystemConnector, SystemConnector<MicroServiceEventType> secondSystemConnector) {
        this.mainSystemConnector = mainSystemConnector;
        this.secondSystemConnector = secondSystemConnector;
    }

    @Override
    public List<Object> sendExternal(Object event, MicroServiceEventType type) {
        try{
            return this.mainSystemConnector.sendExternal(event, type);
        }catch (MessageTransmissionException e){
            System.out.println("Fallback on REST HTTP connection..." + e);
            return this.secondSystemConnector.sendExternal(event, type);
        }
    }
}
