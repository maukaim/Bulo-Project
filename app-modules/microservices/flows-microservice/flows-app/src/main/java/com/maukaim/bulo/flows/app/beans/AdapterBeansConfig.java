package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.common.data.lifecycle.IoTypeAdapter;
import com.maukaim.bulo.common.data.lifecycle.ParameterTypeAdapter;
import com.maukaim.bulo.common.data.lifecycle.impl.IoTypeAdapterImpl;
import com.maukaim.bulo.common.data.lifecycle.impl.ParameterTypeAdapterImpl;
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
import com.maukaim.bulo.flows.data.lifecycle.*;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.*;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterBeansConfig {

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
    public FlowAdapter flowAdapter(OwnerKeyAdapter ownerKeyAdapter,
                                   FlowStageAdapter flowStageAdapter) {
        return new FlowAdapterImpl(ownerKeyAdapter, flowStageAdapter);
    }

    @Bean
    public OwnerKeyAdapter ownerKeyAdapter() {
        return new OwnerKeyAdapterImpl();
    }

    @Bean
    public FlowStageAdapter flowStageAdapter(IoDependencyAdapter ioDependencyAdapter) {
        return new FlowStageAdapterImpl(ioDependencyAdapter);
    }

    @Bean
    public IoDependencyAdapter ioDependencyAdapter(InputProviderAdapter inputProviderAdapter) {
        return new IoDependencyAdapterImpl(inputProviderAdapter);
    }

    @Bean
    public InputProviderAdapter inputProviderAdapter() {
        return new InputProviderAdapterImpl();
    }

    @Bean
    public StageAdapter stageAdapter(ParameterAdapter parameterAdapter) {
        return new StageAdapterImpl(parameterAdapter);
    }

    @Bean
    public ParameterAdapter parameterAdapter() {
        return new ParameterAdapterImpl();
    }

    @Bean
    public StageDefinitionAdapter stageDefinitionAdapter(ParameterDefinitionAdapter parameterDefinitionAdapter,
                                                         StageInputDefinitionAdapter stageInputDefinitionAdapter,
                                                         StageOutputDefinitionAdapter stageOutputDefinitionAdapter) {
        return new StageDefinitionAdapterImpl(parameterDefinitionAdapter, stageInputDefinitionAdapter, stageOutputDefinitionAdapter);
    }

    @Bean
    public ParameterDefinitionAdapter parameterDefinitionAdapter(ParameterTypeAdapter parameterTypeAdapter) {
        return new ParameterDefinitionAdapterImpl(parameterTypeAdapter);
    }

    @Bean
    public ParameterTypeAdapter parameterTypeAdapter() {
        return new ParameterTypeAdapterImpl();
    }

    @Bean
    public StageInputDefinitionAdapter stageInputDefinitionAdapter(IoTypeAdapter ioTypeAdapter) {
        return new StageInputDefinitionAdapterImpl(ioTypeAdapter);
    }

    @Bean
    public StageOutputDefinitionAdapter stageOutputDefinitionAdapter(IoTypeAdapter ioTypeAdapter) {
        return new StageOutputDefinitionAdapterImpl(ioTypeAdapter);
    }

    @Bean
    public IoTypeAdapter ioTypeAdapter(){
        return new IoTypeAdapterImpl();
    }
}
