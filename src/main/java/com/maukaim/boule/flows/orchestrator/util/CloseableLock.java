package com.maukaim.boule.flows.orchestrator.util;

import java.io.Closeable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public interface CloseableLock<ENTITY extends RecordLockable<? extends Lock>> extends Closeable {
    ENTITY getValue();

    @Override
    default void close() {
        try {
            getValue().getEntityLock().unlock();
        } catch (Exception ignored) {
        }
    }

    static <E extends RecordLockable<? extends Lock>> TryLockCloseaseLock<E> of(E entity, long timeout, TimeUnit timeUnit) {
        return new TryLockCloseaseLock<>(entity, timeout, timeUnit);
    }

    static <E extends RecordLockable<? extends Lock>> ObstinateCloseaseLock<E> of(E entity) {
        return new ObstinateCloseaseLock<>(entity);
    }

    class TryLockCloseaseLock<E extends RecordLockable<? extends Lock>> implements CloseableLock<E> {
        private final E value;

        TryLockCloseaseLock(E entity, long timeout, TimeUnit timeUnit) {
            try {
                boolean lockObtained = entity.getEntityLock().tryLock(timeout, timeUnit);
                if (!lockObtained) {
                    throw new EntityLockAcquisitionException(entity, timeout, timeUnit);
                }
                this.value = entity;
            } catch (InterruptedException e) {
                throw new EntityLockAcquisitionException(entity, e);
            }
        }

        @Override
        public E getValue() {
            return value;
        }
    }

    class ObstinateCloseaseLock<E extends RecordLockable<? extends Lock>> implements CloseableLock<E> {
        private final E value;

        ObstinateCloseaseLock(E entity) {
            entity.getEntityLock().lock();
            this.value = entity;
        }

        @Override
        public E getValue() {
            return value;
        }
    }
}
