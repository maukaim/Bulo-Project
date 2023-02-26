package com.maukaim.bulo.executors.core.marshalling;

import com.maukaim.bulo.runners.api.RunnerMarshaller;

import java.util.function.Supplier;

public interface MarshallerProvider extends Supplier<RunnerMarshaller> {
}
