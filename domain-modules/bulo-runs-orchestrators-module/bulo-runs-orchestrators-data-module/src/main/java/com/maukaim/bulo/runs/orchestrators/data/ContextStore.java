package com.maukaim.bulo.runs.orchestrators.data;

import java.util.function.BiFunction;

public interface ContextStore<O extends OrchestrableRunContext<KEY>, KEY> {
    O compute(KEY contextId, BiFunction<KEY, O, O> valueComputer);
}
