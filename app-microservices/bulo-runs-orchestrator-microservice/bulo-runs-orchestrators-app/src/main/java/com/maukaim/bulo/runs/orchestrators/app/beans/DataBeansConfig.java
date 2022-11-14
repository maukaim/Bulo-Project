package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.app.connectors.StageRunConnectorImpl;
import com.maukaim.bulo.runs.orchestrators.app.data.FakeContextProvider;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageDefinitionStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.impl.FunctionalSubStageAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.impl.IoDependencyAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.impl.OutputProviderAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.impl.StageDefinitionAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.InputProviderAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.StageRunAncestorAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.StageRunAncestorDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl.StageRunAncestorAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl.StageRunAncestorDtoAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl.*;
import com.maukaim.bulo.runs.orchestrators.io.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunExecutionEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DataBeansConfig {
    @Configuration
    public static class FlowDataBeansConfig {
        @Bean
        public FlowStore flowStoreImpl() {
            FlowStoreImpl flowStore = new FlowStoreImpl();
            flowStore.put(FakeContextProvider.FLOW_1);
            return flowStore;
        }

        @Bean
        public FlowAdapter flowAdapter(OwnerKeyAdapter ownerKeyAdapter,
                                       FlowStageAdapter flowStageAdapter,
                                       FailureAlternativeRouteAdapter failureAlternativeRouteAdapter) {
            return new FlowAdapterImpl(ownerKeyAdapter, flowStageAdapter, failureAlternativeRouteAdapter);
        }

        @Bean
        public FailureAlternativeRouteAdapter failureAlternativeRouteAdapter(){
            return new FailureAlternativeRouteAdapterImpl();
        }

        @Bean
        OwnerKeyAdapter ownerKeyAdapter() {
            return new OwnerKeyAdapterImpl();
        }

        @Bean
        FlowStageAdapter flowStageAdapter(InputDependencyAdapter inputDependencyAdapter) {
            return new FlowStageAdapterImpl(inputDependencyAdapter);
        }

        @Bean
        public InputDependencyAdapter inputDependencyAdapter(InputProviderAdapter inputProviderAdapter) {
            return new InputDependencyAdapterImpl(inputProviderAdapter);
        }

        @Bean
        public InputProviderAdapter inputProviderAdapter() {
            return new InputProviderAdapterImpl();
        }

        @Bean
        public FlowDtoAdapter flowDtoAdapter(FlowStageDtoAdapter flowStageDtoAdapter,
                                             OwnerKeyDtoAdapter ownerKeyDtoAdapter,
                                             FailureAlternativeRouteDtoAdapter failureAlternativeRouteDtoAdapter) {
            return new FlowDtoAdapterImpl(flowStageDtoAdapter, ownerKeyDtoAdapter, failureAlternativeRouteDtoAdapter);
        }

        @Bean
        public FailureAlternativeRouteDtoAdapter failureAlternativeRouteDtoAdapter(){
            return new FailureAlternativeRouteDtoAdapterImpl();
        }

        @Bean
        public OwnerKeyDtoAdapter ownerKeyDtoAdapter() {
            return new OwnerKeyDtoAdapterImpl();
        }

        @Bean
        public FlowStageDtoAdapter flowStageDtoAdapter(InputDependencyDtoAdapter inputDependencyDtoAdapter) {
            return new FlowStageDtoAdapterImpl(inputDependencyDtoAdapter);
        }

        @Bean
        public InputDependencyDtoAdapter inputDependencyDtoAdapter(InputProviderDtoAdapter inputProviderDtoAdapter) {
            return new InputDependencyDtoAdapterImpl(inputProviderDtoAdapter);
        }

        @Bean
        public InputProviderDtoAdapter inputProviderDtoAdapter() {
            return new InputProviderDtoAdapterImpl();
        }

    }

    @Configuration
    public static class StageRunDataBeansConfig {
        @Bean
        public StageRunStore stageRunCache() {
            return new StageRunStoreImpl();
        }

        @Bean
        public StageRunDtoAdapter stageRunDtoAdapter(StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter,
                                                     ExecutionGraphDtoAdapter executionGraphDtoAdapter,
                                                     OutputProviderDtoAdapter outputProviderDtoAdapter) {
            return new StageRunDtoAdapterImpl(stageRunDependencyDtoAdapter, executionGraphDtoAdapter, outputProviderDtoAdapter);
        }

        @Bean
        public OutputProviderDtoAdapter outputProviderDtoAdapter(){
            return new OutputProviderDtoAdapterImpl();
        }

        @Bean
        public StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter(com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAncestorDtoAdapter stageRunAncestorDtoAdapter) {
            return new StageRunDependencyDtoAdapterImpl(stageRunAncestorDtoAdapter);
        }

        @Bean
        public com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAncestorDtoAdapter stageRunAncestorDtoAdapter() {
            return new com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl.StageRunAncestorDtoAdapterImpl();
        }

        @Bean
        public StageRunAdapter stageRunAdapter(StageRunDependencyAdapter stageRunDependencyAdapter,
                                               ExecutionGraphAdapter executionGraphAdapter,
                                               OutputProviderAdapter outputProviderAdapter) {
            return new StageRunAdapterImpl(executionGraphAdapter, stageRunDependencyAdapter, outputProviderAdapter);
        }

        @Bean
        public StageRunDependencyAdapter stageRunDependencyAdapter(com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAncestorAdapter stageRunAncestorAdapter) {
            return new StageRunDependencyAdapterImpl(stageRunAncestorAdapter);
        }

        @Bean
        public com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAncestorAdapter stageRunAncestorAdapter() {
            return new com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl.StageRunAncestorAdapterImpl();
        }

        @Bean
        public StageRunConnectorImpl stageRunConnector(NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher,
                                                   NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher,
                                                   StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter) {
            return new StageRunConnectorImpl(needStageRunExecutionEventPublisher, needStageRunCancellationEventPublisher, stageRunDependencyDtoAdapter);
        }
    }

    @Configuration
    public static class FlowRunDataBeansConfig {
        @Bean
        public FlowRunStoreImpl flowRunStoreImpl(FlowRunEventPublisher flowRunEventPublisher,
                                                 FlowRunDtoAdapter flowRunDtoAdapter) {
            return new FlowRunStoreImpl(flowRunEventPublisher, flowRunDtoAdapter);
        }

        @Bean
        public FlowRunAdapter flowRunAdapter(StageRunAdapter stageRunAdapter,
                                             ExecutionGraphAdapter executionGraphAdapter) {
            return new FlowRunAdapterImpl(stageRunAdapter, executionGraphAdapter);
        }

        @Bean
        public ExecutionGraphAdapter executionGraphAdapter(FlowStageDependencyAdapter flowStageDependencyAdapter) {
            return new ExecutionGraphAdapterImpl(flowStageDependencyAdapter);
        }

        @Bean
        public FlowStageDependencyAdapter flowStageDependencyAdapter(StageRunAncestorAdapter stageRunAncestorAdapter) {
            return new FlowStageDependencyAdapterImpl(stageRunAncestorAdapter);
        }

        @Bean
        public StageRunAncestorAdapter ancestorAdapter() {
            return new StageRunAncestorAdapterImpl();
        }

        @Bean
        public FlowRunDtoAdapter flowRunDtoAdapter(StageRunDtoAdapter stageRunDtoAdapter,
                                                   ExecutionGraphDtoAdapter executionGraphDtoAdapter) {
            return new FlowRunDtoAdapterImpl(stageRunDtoAdapter, executionGraphDtoAdapter);
        }

        @Bean
        public ExecutionGraphDtoAdapter executionGraphDtoAdapter(FlowStageDependencyDtoAdapter flowStageDependencyDtoAdapter) {
            return new ExecutionGraphDtoAdapterImpl(flowStageDependencyDtoAdapter);
        }

        @Bean
        public FlowStageDependencyDtoAdapter flowStageDependencyDtoAdapter(StageRunAncestorDtoAdapter stageRunAncestorDtoAdapter) {
            return new FlowStageDependencyDtoAdapterImpl(stageRunAncestorDtoAdapter);
        }

        @Bean
        public StageRunAncestorDtoAdapter ancestorDtoAdapter() {
            return new StageRunAncestorDtoAdapterImpl();
        }
    }

    @Configuration
    public static class StageDataBeansConfig {
        @Bean
        public FunctionalStageStore functionalStageStore(){
            return new FunctionalStageStoreImpl(Map.of());
        }
    }

    @Configuration
    public static class DefinitionDataBeansConfig{
        @Bean
        public FunctionalStageDefinitionStore functionalStageDefinitionStore(){
            return new FunctionalStageDefinitionStoreImpl(Map.of());
        }

        @Bean
        public StageDefinitionAdapter stageDefinitionAdapter(FunctionalSubStageAdapter functionalSubStageAdapter,
                                                             OutputProviderAdapter outputProviderAdapter){
            return new StageDefinitionAdapterImpl(functionalSubStageAdapter, outputProviderAdapter);
        }

        @Bean
        public OutputProviderAdapter outputProviderAdapter(){
            return new OutputProviderAdapterImpl();
        }

        @Bean
        public FunctionalSubStageAdapter functionalSubStageAdapter(IoDependencyAdapter ioDependencyAdapter){
            return new FunctionalSubStageAdapterImpl(ioDependencyAdapter);
        }

        @Bean
        public IoDependencyAdapter ioDependencyAdapter(com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.InputProviderAdapter inputProviderAdapter){
            return new IoDependencyAdapterImpl(inputProviderAdapter);
        }

        @Bean
        public com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.InputProviderAdapter fsInputProviderAdapter(){
            return new com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.impl.InputProviderAdapterImpl();
        }
    }
}
