package com.maukaim.bulo.app.shared.servers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maukaim.bulo.app.shared.servers.model.ApplicationEnvironment;
import com.maukaim.bulo.app.shared.servers.model.BuloSystemProperties;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.servers.model.WebServerProperties;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import static com.maukaim.bulo.app.shared.servers.model.ApplicationEnvironment.dev;

public class ServerUtils {
    private static volatile BuloSystemProperties buloSystemProperties = null;
    private static final String BULO_SYSTEM_PROPERTIES_RESOURCE = "system-properties.json";
    private static ApplicationEnvironment applicationEnvironment = dev;

    public static ApplicationEnvironment getApplicationEnvironment(){
        return applicationEnvironment;
    }

    public static WebServerProperties getWebServerProperties(ServiceName name) {
        return getSystemProperty((sysProps) -> sysProps.getServicesMap().get(name));
    }

    public static void setApplicationEnvironment(ApplicationEnvironment app){
        applicationEnvironment = app;
    }

    private static <T> T getSystemProperty(Function<BuloSystemProperties, T> getterFunction) {
        return getterFunction.apply(getBuloSystemProperties());
    }

    public static BuloSystemProperties getBuloSystemProperties() {
        if(buloSystemProperties == null){
            buloSystemProperties = resolveBuloSystemProperties();
        }
        return buloSystemProperties;
    }

    private static BuloSystemProperties resolveBuloSystemProperties(){
        InputStream resourceAsStream = ServerUtils.class.getClassLoader().getResourceAsStream(BULO_SYSTEM_PROPERTIES_RESOURCE);
        String rawJson;
        try {
            rawJson = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Impossible to find System properties json file in classpath.", e);
        }
        try {
            return PropertiesMapper.instance().readValue(rawJson, BuloSystemProperties.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Bulo System properties file has format issues.", e);
        }
    }
}
