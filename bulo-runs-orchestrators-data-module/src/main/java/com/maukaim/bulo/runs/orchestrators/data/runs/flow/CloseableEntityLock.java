package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import java.io.Closeable;

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
