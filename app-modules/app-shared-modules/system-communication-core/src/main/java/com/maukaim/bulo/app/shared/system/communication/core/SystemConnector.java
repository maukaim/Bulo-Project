package com.maukaim.bulo.app.shared.system.communication.core;

import com.maukaim.bulo.app.shared.system.communication.api.SystemEventType;

import java.util.List;

public interface SystemConnector<TYPE extends SystemEventType> {

    /**
     * Send an event to other internal system component
     *
     * @param event object to serialize
     * @param type used to resolve where we connect
     * @return successful sending
     */
    List<Object> sendExternal(Object event, TYPE type);
}
