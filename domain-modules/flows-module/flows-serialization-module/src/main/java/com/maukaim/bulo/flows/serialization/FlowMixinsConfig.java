package com.maukaim.bulo.flows.serialization;


import com.maukaim.bulo.flows.serialization.mixins.CreateFlowInstructionMixIn;
import com.maukaim.bulo.flows.serialization.mixins.FlowDtoMixIn;
import com.maukaim.bulo.flows.serialization.mixins.FlowEventMixIn;
import com.maukaim.bulo.flows.serialization.mixins.FlowStageDtoMixIn;
import com.maukaim.bulo.flows.serialization.mixins.InputProviderDtoMixIn;
import com.maukaim.bulo.flows.serialization.mixins.IoDependencyDtoMixIn;
import com.maukaim.bulo.flows.serialization.mixins.OwnerKeyDtoMixIn;
import com.maukaim.bulo.flows.serialization.mixins.RemoveFlowInstructionMixIn;
import com.maukaim.bulo.io.flows.client.CreateFlowInstruction;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstruction;
import com.maukaim.bulo.io.flows.client.model.FlowDto;
import com.maukaim.bulo.io.flows.client.model.FlowStageDto;
import com.maukaim.bulo.io.flows.client.model.InputProviderDto;
import com.maukaim.bulo.io.flows.client.model.IoDependencyDto;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;
import com.maukaim.bulo.io.flows.system.FlowEvent;

import java.util.Map;

public class FlowMixinsConfig {
    public static Map<Class<?>, Class<?>> SERIALIZATION_JACKSON_MIXIN = Map.of(
            FlowEvent.class, FlowEventMixIn.class,
            CreateFlowInstruction.class, CreateFlowInstructionMixIn.class,
            RemoveFlowInstruction.class, RemoveFlowInstructionMixIn.class,
            FlowDto.class, FlowDtoMixIn.class,
            OwnerKeyDto.class, OwnerKeyDtoMixIn.class,
            FlowStageDto.class, FlowStageDtoMixIn.class,
            IoDependencyDto.class, IoDependencyDtoMixIn.class,
            InputProviderDto.class, InputProviderDtoMixIn.class
    );
}
