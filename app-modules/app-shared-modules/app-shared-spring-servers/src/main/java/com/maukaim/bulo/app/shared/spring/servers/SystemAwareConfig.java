package com.maukaim.bulo.app.shared.spring.servers;

import com.maukaim.bulo.app.shared.servers.ServerUtils;
import com.maukaim.bulo.app.shared.servers.SystemContext;
import com.maukaim.bulo.app.shared.servers.model.ApplicationEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.maukaim.bulo.app.shared.servers.model.ApplicationEnvironment.*;

@Configuration
public class SystemAwareConfig {

    @Bean
    public SystemContext systemContext(Environment environment) {
        resolveAppEnvironment(environment);
        return new SystemContext(ServerUtils.getApplicationEnvironment(), ServerUtils.getBuloSystemProperties());
    }

    private void resolveAppEnvironment(Environment env) {
        String[] activeProfiles = env.getActiveProfiles();
        List<ApplicationEnvironment> applicableEnvironments = Arrays.stream(activeProfiles)
                .map(ApplicationEnvironment::of)
                .filter(Objects::nonNull)
                .toList();

        ApplicationEnvironment applicableEnvironment = dev;
        if (!applicableEnvironments.contains(dev)) {
            if (applicableEnvironments.contains(uat)) {
                applicableEnvironment = uat;
            } else if (applicableEnvironments.contains(prod)) {
                applicableEnvironment = prod;
            }
        }
        ServerUtils.setApplicationEnvironment(applicableEnvironment);
    }
}