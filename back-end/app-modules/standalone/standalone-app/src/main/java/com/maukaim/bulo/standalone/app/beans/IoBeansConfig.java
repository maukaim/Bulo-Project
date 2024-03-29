package com.maukaim.bulo.standalone.app.beans;

import com.maukaim.bulo.data.lifecycle.data.types.client.IoTypeAdapter;
import com.maukaim.bulo.data.lifecycle.data.types.client.IoTypeDtoAdapter;
import com.maukaim.bulo.data.lifecycle.data.types.client.ParameterTypeAdapter;
import com.maukaim.bulo.data.lifecycle.data.types.client.ParameterTypeDtoAdapter;
import com.maukaim.bulo.data.lifecycle.data.types.client.impl.IoTypeAdapterImpl;
import com.maukaim.bulo.data.lifecycle.data.types.client.impl.IoTypeDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.data.types.client.impl.ParameterTypeAdapterImpl;
import com.maukaim.bulo.data.lifecycle.data.types.client.impl.ParameterTypeDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.CreateStageDefinitionConsumer;
import com.maukaim.bulo.data.lifecycle.definitions.client.ParameterDefinitionAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageDefinitionAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageInputDefinitionAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageOutputDefinitionAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.FunctionalSubStageAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.FunctionalSubStageDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.OutputProviderAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.OutputProviderDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.FunctionalSubStageAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.FunctionalSubStageDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.OutputProviderAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.OutputProviderDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.ParameterDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.ParameterDefinitionDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageDefinitionDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageInputDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageInputDefinitionDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageOutputDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageOutputDefinitionDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.FlowAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.FlowDtoAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.FlowStageAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.FlowStageDtoAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.InputProviderAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.InputProviderDtoAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.IoDependencyAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.IoDependencyDtoAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.OwnerKeyAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.OwnerKeyDtoAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.FlowAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.FlowDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.FlowStageAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.FlowStageDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.InputProviderAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.InputProviderDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.IoDependencyAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.IoDependencyDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.OwnerKeyAdapterImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.impl.OwnerKeyDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.stages.client.ParameterAdapter;
import com.maukaim.bulo.data.lifecycle.stages.client.ParameterDtoAdapter;
import com.maukaim.bulo.data.lifecycle.stages.client.StageAdapter;
import com.maukaim.bulo.data.lifecycle.stages.client.StageDtoAdapter;
import com.maukaim.bulo.data.lifecycle.stages.client.impl.ParameterAdapterImpl;
import com.maukaim.bulo.data.lifecycle.stages.client.impl.ParameterDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.stages.client.impl.StageAdapterImpl;
import com.maukaim.bulo.data.lifecycle.stages.client.impl.StageDtoAdapterImpl;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.io.flows.client.CreateFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.client.GetAllFlowsInstructionConsumer;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.io.stages.client.CreateStageEventConsumer;
import com.maukaim.bulo.io.stages.client.DeleteStageEventConsumer;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.standalone.app.io.CreateFlowInstructionConsumerImpl;
import com.maukaim.bulo.standalone.app.io.CreateStageDefinitionConsumerImpl;
import com.maukaim.bulo.standalone.app.io.CreateStageInstructionConsumerImpl;
import com.maukaim.bulo.standalone.app.io.DeleteFlowInstructionConsumerImpl;
import com.maukaim.bulo.standalone.app.io.DeleteStageInstructionConsumerImpl;
import com.maukaim.bulo.standalone.app.io.GetAllFlowsInstructionConsumerImpl;
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
        public GetAllFlowsInstructionConsumer getAllFlowsInstructionConsumer(FlowService flowService,
                                                                             FlowDtoAdapter flowDtoAdapter) {
            return new GetAllFlowsInstructionConsumerImpl(flowService, flowDtoAdapter);
        }

        @Bean
        public FlowDtoAdapter flowDtoAdapter(OwnerKeyDtoAdapter ownerKeyDtoAdapter,
                                             FlowStageDtoAdapter flowStageDtoAdapter) {
            return new FlowDtoAdapterImpl(ownerKeyDtoAdapter, flowStageDtoAdapter);
        }

        @Bean
        public OwnerKeyDtoAdapter ownerKeyDtoAdapter() {
            return new OwnerKeyDtoAdapterImpl();
        }

        @Bean
        public FlowStageDtoAdapter flowStageDtoAdapter(IoDependencyDtoAdapter ioDependencyDtoAdapter) {
            return new FlowStageDtoAdapterImpl(ioDependencyDtoAdapter);
        }

        @Bean
        public IoDependencyDtoAdapter ioDependencyDtoAdapter(InputProviderDtoAdapter inputProviderDtoAdapter) {
            return new IoDependencyDtoAdapterImpl(inputProviderDtoAdapter);
        }

        @Bean
        public InputProviderDtoAdapter inputProviderDtoAdapter() {
            return new InputProviderDtoAdapterImpl();
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

    @Configuration
    public static class DefinitionIoConfig {
        @Bean
        public CreateStageDefinitionConsumer stageDefinitionCreateInstructionConsumer(StageDefinitionService stageDefinitionService,
                                                                                      StageDefinitionAdapter stageDefinitionAdapter) {
            return new CreateStageDefinitionConsumerImpl(stageDefinitionService, stageDefinitionAdapter);
        }

        @Bean
        public StageDefinitionAdapter stageDefinitionAdapter(ParameterDefinitionAdapter parameterDefinitionAdapter,
                                                             StageInputDefinitionAdapter stageInputDefinitionAdapter,
                                                             StageOutputDefinitionAdapter stageOutputDefinitionAdapter,
                                                             FunctionalSubStageAdapter functionalSubStageAdapter,
                                                             OutputProviderAdapter outputProviderAdapter) {
            return new StageDefinitionAdapterImpl(parameterDefinitionAdapter, stageInputDefinitionAdapter, stageOutputDefinitionAdapter, functionalSubStageAdapter, outputProviderAdapter);
        }

        @Bean
        public OutputProviderAdapter ioOutputProviderAdapter() {
            return new OutputProviderAdapterImpl();
        }

        @Bean
        public ParameterDefinitionAdapter parameterDefinitionAdapterImpl(ParameterTypeAdapter parameterTypeAdapter) {
            return new ParameterDefinitionAdapterImpl(parameterTypeAdapter);
        }

        @Bean
        public ParameterTypeAdapter parameterTypeAdapter() {
            return new ParameterTypeAdapterImpl();
        }

        @Bean
        public StageInputDefinitionAdapter stageInputDefinitionAdapterImpl(IoTypeAdapter ioTypeAdapter) {
            return new StageInputDefinitionAdapterImpl(ioTypeAdapter);
        }

        @Bean
        public StageOutputDefinitionAdapter stageOutputDefinitionAdapterImpl(IoTypeAdapter ioTypeAdapter) {
            return new StageOutputDefinitionAdapterImpl(ioTypeAdapter);
        }

        @Bean
        public IoTypeAdapter ioTypeAdapter() {
            return new IoTypeAdapterImpl();
        }

        @Bean
        public FunctionalSubStageAdapter functionalSubStageAdapter(com.maukaim.bulo.data.lifecycle.definitions.client.functional.IoDependencyAdapter ioDependencyAdapter) {
            return new FunctionalSubStageAdapterImpl(ioDependencyAdapter);
        }

        @Bean
        public com.maukaim.bulo.data.lifecycle.definitions.client.functional.IoDependencyAdapter fsDefinitionIoDependencyAdapter(com.maukaim.bulo.data.lifecycle.definitions.client.functional.InputProviderAdapter inputProviderAdapter) {
            return new com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.IoDependencyAdapterImpl(inputProviderAdapter);
        }

        @Bean
        public com.maukaim.bulo.data.lifecycle.definitions.client.functional.InputProviderAdapter fsDefinitionInputProviderAdapter() {
            return new com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.InputProviderAdapterImpl();
        }

        @Bean
        public StageDefinitionDtoAdapter stageDefinitionDtoAdapter(ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
                                                                   StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
                                                                   StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter,
                                                                   FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter,
                                                                   OutputProviderDtoAdapter outputProviderDtoAdapter) {
            return new StageDefinitionDtoAdapterImpl(parameterDefinitionDtoAdapter, stageInputDefinitionDtoAdapter, stageOutputDefinitionDtoAdapter, functionalSubStageDtoAdapter, outputProviderDtoAdapter);
        }

        @Bean
        public OutputProviderDtoAdapter outputProviderDtoAdapter() {
            return new OutputProviderDtoAdapterImpl();
        }

        @Bean
        public ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapterImpl(ParameterTypeDtoAdapter parameterTypeDtoAdapter) {
            return new ParameterDefinitionDtoAdapterImpl(parameterTypeDtoAdapter);
        }

        @Bean
        public ParameterTypeDtoAdapter parameterTypeDtoAdapter() {
            return new ParameterTypeDtoAdapterImpl();
        }

        @Bean
        public StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapterImpl(IoTypeDtoAdapter ioTypeDtoAdapter) {
            return new StageInputDefinitionDtoAdapterImpl(ioTypeDtoAdapter);
        }

        @Bean
        public StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapterImpl(IoTypeDtoAdapter ioTypeDtoAdapter) {
            return new StageOutputDefinitionDtoAdapterImpl(ioTypeDtoAdapter);
        }

        @Bean
        public IoTypeDtoAdapter ioTypeDtoAdapter() {
            return new IoTypeDtoAdapterImpl();
        }

        @Bean
        public FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter(com.maukaim.bulo.data.lifecycle.definitions.client.functional.IoDependencyDtoAdapter ioDependencyDtoAdapter) {
            return new FunctionalSubStageDtoAdapterImpl(ioDependencyDtoAdapter);
        }

        @Bean
        public com.maukaim.bulo.data.lifecycle.definitions.client.functional.IoDependencyDtoAdapter ioDependencyDtoAdapterImpl(com.maukaim.bulo.data.lifecycle.definitions.client.functional.InputProviderDtoAdapter inputProviderDtoAdapter) {
            return new com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.IoDependencyDtoAdapterImpl(inputProviderDtoAdapter);
        }

        @Bean
        public com.maukaim.bulo.data.lifecycle.definitions.client.functional.InputProviderDtoAdapter inputProviderDtoAdapterImpl() {
            return new com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.InputProviderDtoAdapterImpl();
        }
    }

}
