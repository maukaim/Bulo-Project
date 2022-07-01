package com.maukaim.bulo.runs.orchestrator.flow.run;

import java.io.Closeable;

/**
 * Represent a run memory.
 * - What is the next step?
 * - Is the RUn terminated?
 * - Can I start to run?
 */
public interface FlowRunLocked extends Closeable {
    FlowRun getValue();
}
