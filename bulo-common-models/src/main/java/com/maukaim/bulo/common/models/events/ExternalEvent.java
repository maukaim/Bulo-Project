package com.maukaim.bulo.common.models.events;

import java.time.Instant;

public interface ExternalEvent {
    Instant getInstant();
}
