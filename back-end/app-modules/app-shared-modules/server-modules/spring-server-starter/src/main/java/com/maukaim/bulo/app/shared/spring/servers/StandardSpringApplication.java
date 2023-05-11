package com.maukaim.bulo.app.shared.spring.servers;

import com.maukaim.bulo.shared.server.core.ServerUtils;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.shared.server.core.model.WebServerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;

public abstract class StandardSpringApplication {
    private static final String SPRING_PORT_PROPERTY = "server.port";

    protected static ConfigurableApplicationContext launchSpringApp(ServiceName serviceName, Class<?> primarySource, String... args) {
        SpringApplication app = new SpringApplication(primarySource);
        app.addInitializers(new ServiceNameInitializer(serviceName));
        configure(app, serviceName);
        return app.run(args);
    }

    private static void configure(SpringApplication app, ServiceName serviceName) {
        WebServerProperties webServerProperties = ServerUtils.getWebServerProperties(serviceName);
        app.setDefaultProperties(Collections
                .singletonMap(SPRING_PORT_PROPERTY, webServerProperties.getPort()));
    }
}
