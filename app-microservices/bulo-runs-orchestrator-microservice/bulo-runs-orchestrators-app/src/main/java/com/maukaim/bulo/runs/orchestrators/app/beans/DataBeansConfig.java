package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.app.connectors.StageRunConnectorImpl;
import com.maukaim.bulo.runs.orchestrators.app.data.FakeContextProvider;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.FlowRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.FlowStoreImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.StageRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.*;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl.*;
import com.maukaim.bulo.runs.orchestrators.io.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunExecutionEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        public FlowAdapter flowAdapter(OwnerKeyAdapter ownerKeyAdapter, FlowStageAdapter flowStageAdapter) {
            return new FlowAdapterImpl(ownerKeyAdapter, flowStageAdapter);
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
        public FlowDtoAdapter flowDtoAdapter(FlowStageDtoAdapter flowStageDtoAdapter, OwnerKeyDtoAdapter ownerKeyDtoAdapter) {
            return new FlowDtoAdapterImpl(flowStageDtoAdapter, ownerKeyDtoAdapter);
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
                                                     ExecutionGraphDtoAdapter executionGraphDtoAdapter) {
            return new StageRunDtoAdapterImpl(stageRunDependencyDtoAdapter, executionGraphDtoAdapter);
        }

        @Bean
        public StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter(StageRunAncestorDtoAdapter stageRunAncestorDtoAdapter) {
            return new StageRunDependencyDtoAdapterImpl(stageRunAncestorDtoAdapter);
        }

        @Bean
        public StageRunAncestorDtoAdapter stageRunAncestorDtoAdapter() {
            return new StageRunAncestorDtoAdapterImpl();
        }

        @Bean
        public StageRunAdapter stageRunAdapter(StageRunDependencyAdapter stageRunDependencyAdapter,
                                               ExecutionGraphAdapter executionGraphAdapter) {
            return new StageRunAdapterImpl(executionGraphAdapter, stageRunDependencyAdapter);
        }

        @Bean
        public StageRunDependencyAdapter stageRunDependencyAdapter(StageRunAncestorAdapter stageRunAncestorAdapter) {
            return new StageRunDependencyAdapterImpl(stageRunAncestorAdapter);
        }

        @Bean
        public StageRunAncestorAdapter stageRunAncestorAdapter() {
            return new StageRunAncestorAdapterImpl();
        }

        @Bean
        public StageRunConnector stageRunConnector(NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher,
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
        public FlowStageDependencyAdapter flowStageDependencyAdapter(FlowStageAncestorAdapter flowStageAncestorAdapter) {
            return new FlowStageDependencyAdapterImpl(flowStageAncestorAdapter);
        }

        @Bean
        public FlowStageAncestorAdapter ancestorAdapter() {
            return new FlowStageAncestorAdapterImpl();
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
        public FlowStageDependencyDtoAdapter flowStageDependencyDtoAdapter(FlowStageAncestorDtoAdapter flowStageAncestorDtoAdapter) {
            return new FlowStageDependencyDtoAdapterImpl(flowStageAncestorDtoAdapter);
        }

        @Bean
        public FlowStageAncestorDtoAdapter ancestorDtoAdapter() {
            return new FlowStageAncestorDtoAdapterImpl();
        }
    }
}
