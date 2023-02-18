package com.maukaim.bulo.mockingbird.builders;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.io.flows.flow.FlowDto;
import com.maukaim.bulo.io.flows.flow.FlowStageDto;
import com.maukaim.bulo.io.flows.flow.InputProviderDto;
import com.maukaim.bulo.io.flows.flow.IoDependencyDto;
import com.maukaim.bulo.io.flows.flow.OwnerKeyDto;
import com.maukaim.bulo.io.flows.flow.OwnerKeyTypeDto;

import java.util.HashSet;
import java.util.Set;

public class FlowDtoBuilder {
    private String flowId;
    private Set<OwnerKeyDto> ownerKeys;
    private Set<FlowStageDto> flowStages;
    private Boolean allowParallelRun;

    public static FlowDtoBuilder aFlow(){
        return new FlowDtoBuilder();
    }

    public static IoDependencyDto ioDependency(String inputId, InputProviderDto... inputProviders){
        return new IoDependencyDto(inputId, Set.of(inputProviders));
    }

    public static InputProviderDto inputProvider(ContextStageId contextStageId, String outputIds){
        return new InputProviderDto(contextStageId, Set.of(outputIds));
    }

    private FlowDtoBuilder() {
    }

    public FlowDtoBuilder withFlowId(String flowId){
        this.flowId = flowId;
        return this;
    }

    public FlowDtoBuilder withOwnerKey(String ownerId, OwnerKeyTypeDto type){
        if(ownerKeys == null){
            this.ownerKeys = new HashSet<>();
        }
        this.ownerKeys.add(new OwnerKeyDto(ownerId, type));
        return this;
    }

    public FlowDtoBuilder withFlowStage(ContextStageId stageId, IoDependencyDto... ioDependencies){
        if(flowStages == null){
            this.flowStages = new HashSet<>();
        }
        flowStages.add(new FlowStageDto(stageId, Set.of(ioDependencies)));
        return this;
    }

    public FlowDtoBuilder withAllowParallelRun(boolean allowParallelRun){
        this.allowParallelRun = allowParallelRun;
        return this;
    }

    public FlowDto build(){
        return new FlowDto(
                flowId,
                ownerKeys,
                flowStages,
                allowParallelRun
        );
    }

}
