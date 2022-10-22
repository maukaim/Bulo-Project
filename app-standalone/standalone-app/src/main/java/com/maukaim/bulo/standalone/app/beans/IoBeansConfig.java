package com.maukaim.bulo.standalone.app.beans;

import com.maukaim.bulo.definitions.data.lifecycle.ParameterDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageInputDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.StageOutputDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.functional.FunctionalSubStageAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.functional.impl.FunctionalSubStageAdapterImpl;
import com.maukaim.bulo.definitions.data.lifecycle.impl.ParameterDefinitionAdapterImpl;
import com.maukaim.bulo.definitions.data.lifecycle.impl.StageDefinitionAdapterImpl;
import com.maukaim.bulo.definitions.data.lifecycle.impl.StageInputDefinitionAdapterImpl;
import com.maukaim.bulo.definitions.data.lifecycle.impl.StageOutputDefinitionAdapterImpl;
import com.maukaim.bulo.definitions.io.StageDefinitionCreateInstructionConsumer;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.data.lifecycle.*;
import com.maukaim.bulo.flows.data.lifecycle.impl.*;
import com.maukaim.bulo.flows.io.CreateFlowInstructionConsumer;
import com.maukaim.bulo.flows.io.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.data.lifecycle.ParameterAdapter;
import com.maukaim.bulo.stages.data.lifecycle.ParameterDtoAdapter;
import com.maukaim.bulo.stages.data.lifecycle.StageAdapter;
import com.maukaim.bulo.stages.data.lifecycle.StageDtoAdapter;
import com.maukaim.bulo.stages.data.lifecycle.impl.ParameterAdapterImpl;
import com.maukaim.bulo.stages.data.lifecycle.impl.ParameterDtoAdapterImpl;
import com.maukaim.bulo.stages.data.lifecycle.impl.StageAdapterImpl;
import com.maukaim.bulo.stages.data.lifecycle.impl.StageDtoAdapterImpl;
import com.maukaim.bulo.stages.io.CreateStageEventConsumer;
import com.maukaim.bulo.stages.io.DeleteStageEventConsumer;
import com.maukaim.bulo.standalone.app.io.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoBeansConfig {
    @Configuration
    public static class FlowIoConfig {
        @Bean
        public RemoveFlowInstructionConsumer deleteFlowInstructionConsumer(FlowService flowService) {
            return new DeleteFlowInstructionConsumerImpl(flowService);
        }

        @Bean
        public CreateFlowInstructionConsumer createFlowInstructionConsumer(FlowService flowService,
                                                                           FlowAdapter flowAdapter) {
            return new CreateFlowInstructionConsumerImpl(flowService, flowAdapter);
        }

        @Bean
        public StageDefinitionCreateInstructionConsumer stageDefinitionCreateInstructionConsumer(StageDefinitionService stageDefinitionService,
                                                                                                 StageDefinitionAdapter stageDefinitionAdapter) {
            return new CreateStageDefinitionInstructionImpl(stageDefinitionService, stageDefinitionAdapter);
        }

        @Bean
        public StageDefinitionAdapter stageDefinitionAdapter(ParameterDefinitionAdapter parameterDefinitionAdapter,
                                                             StageInputDefinitionAdapter stageInputDefinitionAdapter,
                                                             StageOutputDefinitionAdapter stageOutputDefinitionAdapter,
                                                             FunctionalSubStageAdapter functionalSubStageAdapter) {
            return new StageDefinitionAdapterImpl(parameterDefinitionAdapter, stageInputDefinitionAdapter, stageOutputDefinitionAdapter, functionalSubStageAdapter);
        }

        @Bean
        public ParameterDefinitionAdapter parameterDefinitionAdapter() {
            return new ParameterDefinitionAdapterImpl();
        }

        @Bean
        public StageInputDefinitionAdapter stageInputDefinitionAdapter() {
            return new StageInputDefinitionAdapterImpl();
        }

        @Bean
        public StageOutputDefinitionAdapter stageOutputDefinitionAdapter() {
            return new StageOutputDefinitionAdapterImpl();
        }

        @Bean
        public FunctionalSubStageAdapter functionalSubStageAdapter(com.maukaim.bulo.definitions.data.lifecycle.functional.IoDependencyAdapter ioDependencyAdapter) {
            return new FunctionalSubStageAdapterImpl(ioDependencyAdapter);
        }

        @Bean
        public com.maukaim.bulo.definitions.data.lifecycle.functional.IoDependencyAdapter fsDefinitionIoDependencyAdapter(com.maukaim.bulo.definitions.data.lifecycle.functional.InputProviderAdapter inputProviderAdapter) {
            return new com.maukaim.bulo.definitions.data.lifecycle.functional.impl.IoDependencyAdapterImpl(inputProviderAdapter);
        }

        @Bean
        public com.maukaim.bulo.definitions.data.lifecycle.functional.InputProviderAdapter fsDefinitionInputProviderAdapter() {
            return new com.maukaim.bulo.definitions.data.lifecycle.functional.impl.InputProviderAdapterImpl();
        }

        @Bean
        public FlowAdapter flowIoAdapter(OwnerKeyAdapter ownerKeyAdapter,
                                         FlowStageAdapter flowStageAdapter) {
            return new FlowAdapterImpl(ownerKeyAdapter, flowStageAdapter);
        }

        @Bean
        public OwnerKeyAdapter ownerKeyIoAdapter() {
            return new OwnerKeyAdapterImpl();
        }

        @Bean
        public FlowStageAdapter flowStageIoAdapter(IoDependencyAdapter ioDependencyAdapter) {
            return new FlowStageAdapterImpl(ioDependencyAdapter);
        }

        @Bean
        public IoDependencyAdapter ioDependencyIoAdapter(InputProviderAdapter inputProviderAdapter) {
            return new IoDependencyAdapterImpl(inputProviderAdapter);
        }

        @Bean
        public InputProviderAdapter inputProviderIoAdapter() {
            return new InputProviderAdapterImpl();
        }
    }

    @Configuration
    public static class StageIoConfig {
        @Bean
        public DeleteStageEventConsumer deleteStageEventConsumer(StageService service) {
            return new DeleteStageInstructionConsumerImpl(service);
        }

        @Bean
        public CreateStageEventConsumer createStageEventConsumer(StageService stageService,
                                                                 StageAdapter stageAdapter) {
            return new CreateStageInstructionConsumerImpl(stageService, stageAdapter);
        }

        @Bean
        public StageAdapter stageIoAdapter(ParameterAdapter parameterAdapter) {
            return new StageAdapterImpl(parameterAdapter);
        }

        @Bean
        public ParameterAdapter parameterIoAdapter() {
            return new ParameterAdapterImpl();
        }

        @Bean
        public StageDtoAdapter stageDtoIoAdapter(ParameterDtoAdapter parameterDtoAdapter) {
            return new StageDtoAdapterImpl(parameterDtoAdapter);
        }

        @Bean
        public ParameterDtoAdapter parameterDtoIoAdapter() {
            return new ParameterDtoAdapterImpl();
        }
    }

}
