package com.maukaim.boule.flows.orchestrator.util;

import java.io.Closeable;
import java.io.IOException;

public abstract class CloseableEntityLock<ENTITY> implements Closeable {
    protected final ENTITY entity;

    public static <E> CloseableEntityLock<E> of(E entity, Runnable unlockRunnable) {
        return new CloseableEntityLock<>(entity) {
            @Override
            public void close() {
                unlockRunnable.run();
            }
        };
    }

    private CloseableEntityLock(ENTITY entity) {
        this.entity = entity;
    }

    public ENTITY getEntity() {
        return entity;
    }

    @Override
    public abstract void close();
}
