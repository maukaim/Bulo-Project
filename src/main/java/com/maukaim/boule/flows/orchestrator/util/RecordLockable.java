package com.maukaim.boule.flows.orchestrator.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.concurrent.locks.Lock;

public interface RecordLockable<L extends Lock> {
    @JsonIgnore
    L getEntityLock();
}
