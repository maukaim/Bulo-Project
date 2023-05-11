package com.maukaim.bulo.executors.core;

import java.util.concurrent.Future;

/**
 * Execute asynchronously runnables passed.
 * Abstraction layer for implementation as ExecutorService from java.util.concurrent.
 * Can i.e add a Queue capability to implementors of this interface if the app is meant to have high throughput.
 */
public interface RunExecutor {
    Future<?> async(Runnable stageRun);
}
