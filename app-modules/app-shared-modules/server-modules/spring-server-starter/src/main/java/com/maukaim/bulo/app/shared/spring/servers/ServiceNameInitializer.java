package com.maukaim.bulo.app.shared.spring.servers;


import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ServiceNameInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private final ServiceName serviceName;

    public ServiceNameInitializer(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getBeanFactory();

        GenericBeanDefinition serviceNameBeanDefinition = new GenericBeanDefinition();
        serviceNameBeanDefinition.setBeanClass(ServiceName.class);
        serviceNameBeanDefinition.setInstanceSupplier(() -> serviceName);
        serviceNameBeanDefinition.setScope("singleton");

        registry.registerBeanDefinition("serviceName", serviceNameBeanDefinition);
    }
}