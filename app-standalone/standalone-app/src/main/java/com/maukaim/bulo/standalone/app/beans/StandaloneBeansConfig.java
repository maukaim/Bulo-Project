package com.maukaim.bulo.standalone.app.beans;

import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.executors.core.StageRunnerRegistry;
import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageDefinitionStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;
import com.maukaim.bulo.stages.models.StageDefinitionStore;
import com.maukaim.bulo.standalone.app.connectivity.StageDefinitionInstructorImpl;
import com.maukaim.bulo.standalone.data.lifecycle.StageDefinitionInstructor;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.MainDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.*;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl.*;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores.ExecutorModuleDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores.FlowModuleDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores.OrchestratorModuleFunctionalStageDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores.StageModuleDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.flows.MainFlowStore;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.InputProviderAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.*;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.impl.InputProviderAdapterImpl;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.impl.*;
import com.maukaim.bulo.standalone.data.lifecycle.flows.sub.stores.OrchestratorModuleFlowStore;
import com.maukaim.bulo.standalone.data.lifecycle.runs.MainFlowRunStore;
import com.maukaim.bulo.standalone.data.lifecycle.runs.MainStageRunResultStore;
import com.maukaim.bulo.standalone.data.lifecycle.runs.MainStageRunStore;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunAncestorAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunDependencyAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.impl.StageRunAncestorAdapterImpl;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.impl.StageRunDependencyAdapterImpl;
import com.maukaim.bulo.standalone.data.lifecycle.stages.MainStageStore;
import com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.ParameterAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.StageAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.impl.ParameterAdapterImpl;
import com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.impl.StageAdapterImpl;
import com.maukaim.bulo.standalone.data.lifecycle.stages.sub.stores.DefinitionModuleStageStore;
import com.maukaim.bulo.standalone.data.lifecycle.stages.sub.stores.ExecutorModuleStageStore;
import com.maukaim.bulo.standalone.data.lifecycle.stages.sub.stores.FlowModuleStageStore;
import com.maukaim.bulo.standalone.data.lifecycle.stages.sub.stores.OrchestratorModuleFunctionalStageStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

@Configuration
public class StandaloneBeansConfig {
    @Configuration
    public static class StageDataConfig {
        @Bean
        public MainStageStore mainStageStore() {
            return new MainStageStore(new HashMap<>());
        }

        @Bean
        public FunctionalStageStore orchestratorModuleStageStore(MainStageStore mainStageStore) {
            return new OrchestratorModuleFunctionalStageStore(mainStageStore);
        }

        @Bean
        public com.maukaim.bulo.executors.data.StageStore executorModuleStageStore(MainStageStore mainStageStore,
                                                                                   StageAdapter stageAdapter) {
            return new ExecutorModuleStageStore(mainStageStore, stageAdapter);
        }

        @Bean
        public com.maukaim.bulo.flows.data.StageStore flowModuleStageStore(MainStageStore mainStageStore,
                                                                           StageAdapter stageAdapter) {
            return new FlowModuleStageStore(mainStageStore, stageAdapter);
        }

        @Bean
        public com.maukaim.bulo.definitions.data.StageStore definitionModuleStageStore(MainStageStore mainStageStore,
                                                                                       StageAdapter stageAdapter) {
            return new DefinitionModuleStageStore(mainStageStore, stageAdapter);
        }

        @Bean
        public StageAdapter stageAdapter(ParameterAdapter parameterAdapter) {
            return new StageAdapterImpl(parameterAdapter);
        }

        @Bean
        public ParameterAdapter parameterAdapter() {
            return new ParameterAdapterImpl();
        }

    }

    @Configuration
    public static class FlowDataConfig {
        @Bean
        public MainFlowStore mainFlowStore() {
            return new MainFlowStore(new HashMap<>());
        }

        @Bean
        public FlowStore orchestratorModuleFlowStore(MainFlowStore mainFlowStore,
                                                     FlowAdapter flowAdapter) {
            return new OrchestratorModuleFlowStore(mainFlowStore, flowAdapter);
        }

        @Bean
        public FlowAdapter flowAdapter(OwnerKeyAdapter ownerKeyAdapter,
                                       FlowStageAdapter flowStageAdapter) {
            return new FlowAdapterImpl(ownerKeyAdapter, flowStageAdapter);
        }

        @Bean
        public OwnerKeyAdapter ownerKeyAdapter() {
            return new OwnerKeyAdapterImpl();
        }

        @Bean
        public FlowStageAdapter flowStageAdapter(InputDependencyAdapter inputDependencyAdapter) {
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
    }

    @Configuration
    public static class DefinitionDataConfig {
        @Bean
        public MainDefinitionStore mainDefinitionStore() {
            return new MainDefinitionStore();
        }

        @Bean
        public com.maukaim.bulo.flows.data.StageDefinitionStore flowModuleDefinitionStore(MainDefinitionStore mainDefinitionStore,
                                                                                          StageDefinitionAdapter definitionAdapter) {
            return new FlowModuleDefinitionStore(mainDefinitionStore, definitionAdapter);
        }

        @Bean
        public com.maukaim.bulo.executors.data.StageDefinitionStore executorModuleDefinitionStore(StageRunnerRegistry runnerRegistry,
                                                                                                  StageDefinitionInstructor instructor) {
            List<StageDefinition> allDefinitions = runnerRegistry.getAllDefinitions();
            ExecutorModuleDefinitionStore executorModuleDefinitionStore = new ExecutorModuleDefinitionStore(instructor, new HashMap<>());
            executorModuleDefinitionStore.putAll(allDefinitions);
            return executorModuleDefinitionStore;
        }

        @Bean
        public StageDefinitionStore stageModuleDefinitionStore(MainDefinitionStore mainDefinitionStore,
                                                               StageDefinitionAdapter definitionAdapter) {
            return new StageModuleDefinitionStore(mainDefinitionStore, definitionAdapter);
        }

        @Bean
        public FunctionalStageDefinitionStore functionalStageDefinitionStore(MainDefinitionStore mainDefinitionStore,
                                                                             StageDefinitionAdapter stageDefinitionAdapter) {
            return new OrchestratorModuleFunctionalStageDefinitionStore(mainDefinitionStore, stageDefinitionAdapter);
        }

        @Bean
        public StageDefinitionInstructor definitionInstructor(StageDefinitionService stageDefinitionService,
                                                              StageDefinitionAdapter definitionAdapter) {
            return new StageDefinitionInstructorImpl(stageDefinitionService, definitionAdapter);
        }

        @Bean
        public StageDefinitionAdapter definitionAdapter(StageInputDefinitionAdapter inputDefinitionAdapter,
                                                        StageOutputDefinitionAdapter outputDefinitionAdapter,
                                                        ParameterDefinitionAdapter parameterDefinitionAdapter,
                                                        FsStageAdapter fsStageAdapter,
                                                        OutputProviderAdapter outputProviderAdapter) {
            return new StageDefinitionAdapterImpl(inputDefinitionAdapter, outputDefinitionAdapter, parameterDefinitionAdapter, fsStageAdapter, outputProviderAdapter);
        }

        @Bean
        public OutputProviderAdapter outputProviderAdapter(){
            return new OutputProviderAdapterImpl();
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
        public ParameterDefinitionAdapter parameterDefinitionAdapter() {
            return new ParameterDefinitionAdapterImpl();
        }

        @Bean
        public FsStageAdapter fsStageAdapter(IoDependencyAdapter ioDependencyAdapter) {
            return new FsStageAdapterImpl(ioDependencyAdapter);
        }

        @Bean
        public IoDependencyAdapter definitionIoDependencyAdapter(com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.InputProviderAdapter inputProviderAdapter) {
            return new IoDependencyAdapterImpl(inputProviderAdapter);
        }

        @Bean
        public com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.InputProviderAdapter definitionInputProviderAdapter() {
            return new com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl.InputProviderAdapterImpl();
        }
    }

    @Configuration
    public static class RunDataConfig {
        @Bean
        public MainFlowRunStore flowRunStore() {
            return new MainFlowRunStore(new HashMap<>());
        }

        @Bean
        public MainStageRunStore stageRunStore() {
            return new MainStageRunStore(new HashMap<>());
        }

        @Bean
        public MainStageRunResultStore stageRunResultStore() {
            return new MainStageRunResultStore(new HashMap<>());
        }

        @Bean
        public StageRunDependencyAdapter stageRunDependencyAdapter(StageRunAncestorAdapter ancestorAdapter) {
            return new StageRunDependencyAdapterImpl(ancestorAdapter);
        }

        @Bean
        public StageRunAncestorAdapter stageRunAncestorAdapter() {
            return new StageRunAncestorAdapterImpl();
        }
    }

}
