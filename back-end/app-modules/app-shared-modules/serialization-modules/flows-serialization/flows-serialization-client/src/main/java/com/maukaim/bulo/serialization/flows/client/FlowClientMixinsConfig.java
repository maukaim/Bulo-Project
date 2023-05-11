package com.maukaim.bulo.serialization.flows.client;


import com.maukaim.bulo.io.flows.client.CreateFlowInstruction;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstruction;
import com.maukaim.bulo.io.flows.client.model.FlowDto;
import com.maukaim.bulo.io.flows.client.model.FlowStageDto;
import com.maukaim.bulo.io.flows.client.model.InputDependencyDto;
import com.maukaim.bulo.io.flows.client.model.InputProviderDto;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;
import com.maukaim.bulo.serialization.flows.client.mixins.CreateFlowInstructionMixIn;
import com.maukaim.bulo.serialization.flows.client.mixins.FlowDtoMixIn;
import com.maukaim.bulo.serialization.flows.client.mixins.FlowStageDtoMixIn;
import com.maukaim.bulo.serialization.flows.client.mixins.InputProviderDtoMixIn;
import com.maukaim.bulo.serialization.flows.client.mixins.IoDependencyDtoMixIn;
import com.maukaim.bulo.serialization.flows.client.mixins.OwnerKeyDtoMixIn;
import com.maukaim.bulo.serialization.flows.client.mixins.RemoveFlowInstructionMixIn;

import java.util.Map;

public class FlowClientMixinsConfig {
    public static final Map<Class<?>, Class<?>> FLOW_CLIENT_SERIALIZATION_JACKSON_MIXIN = Map.of(
            CreateFlowInstruction.class, CreateFlowInstructionMixIn.class,
            RemoveFlowInstruction.class, RemoveFlowInstructionMixIn.class,
            FlowDto.class, FlowDtoMixIn.class,
            OwnerKeyDto.class, OwnerKeyDtoMixIn.class,
            FlowStageDto.class, FlowStageDtoMixIn.class,
            InputDependencyDto.class, IoDependencyDtoMixIn.class,
            InputProviderDto.class, InputProviderDtoMixIn.class
    );
}
