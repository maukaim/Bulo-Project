package com.maukaim.boule.flows.orchestrator.util;

import java.util.concurrent.TimeUnit;

public class EntityLockAcquisitionException extends RuntimeException {

    public EntityLockAcquisitionException(RecordLockable<?> recordLockable, long timeout, TimeUnit timeUnit){
        super(String.format("Lock of Lockable %s not acquired by Thread %s, %s %s timeout reached.",
                recordLockable,
                Thread.currentThread().getId(),
                timeout,
                timeUnit.name()));
    }

    public EntityLockAcquisitionException(RecordLockable<?> recordLockable, Throwable throwable){
        super(String.format("Thread %s interrupted while acquiring lockable %s ",
                Thread.currentThread().getId(),
                recordLockable), throwable);
    }

    public EntityLockAcquisitionException(String msg){
        super(msg);
    }
}
