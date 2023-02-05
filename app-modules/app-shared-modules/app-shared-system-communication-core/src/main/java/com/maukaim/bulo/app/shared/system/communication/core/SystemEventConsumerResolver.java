package com.maukaim.bulo.app.shared.system.communication.core;

import com.maukaim.bulo.app.shared.system.communication.api.SystemEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemEventConsumer;

import java.util.List;

/**
 * Should be defined at the App level.
 * Only apps can know exactly where/what to contact on event;
 * @param <T>
 */
public interface SystemEventConsumerResolver<T extends SystemEventConsumer, TYPE extends SystemEventType> {

    List<T> resolve(TYPE eventType);
}
